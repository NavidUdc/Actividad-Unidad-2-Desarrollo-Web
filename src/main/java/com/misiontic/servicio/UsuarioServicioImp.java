package com.misiontic.servicio;

import com.misiontic.modelo.Usuario;
import com.misiontic.repositorio.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.mail.internet.MimeMessage;

@Service
public class UsuarioServicioImp implements IUsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private JavaMailSender mailSender;

    // L — Listar todos los usuarios
    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listarTodos() {
        return (List<Usuario>) usuarioRepositorio.findAll();
    }

    // C — Guardar nuevo usuario
    @Transactional
    @Override
    public void guardar(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    // U — Actualizar usuario
    @Transactional
    @Override
    public void actualizar(Usuario usuario) {
        Usuario existente = buscarPorCedula(usuario.getCedula());
        if (existente != null) {
            if (usuario.getClave() == null || usuario.getClave().isEmpty()) {
                usuario.setClave(existente.getClave());
            }
        }
        usuarioRepositorio.save(usuario);
    }

    // D — Eliminar por cedula
    @Transactional
    @Override
    public void eliminar(String cedula) {
        usuarioRepositorio.deleteById(cedula);
    }

    // R — Buscar por cedula
    @Transactional(readOnly = true)
    @Override
    public Usuario buscarPorCedula(String cedula) {
        Optional<Usuario> resultado = usuarioRepositorio.findById(cedula);
        return resultado.orElse(null);
    }

    // R — Buscar por email
    @Transactional(readOnly = true)
    @Override
    public Usuario buscarPorEmail(String email) {
        Optional<Usuario> resultado = usuarioRepositorio.findByEmail(email);
        return resultado.orElse(null);
    }

    // Recuperar contraseña: genera clave temporal en texto plano y envia correo
    @Transactional
    @Override
    public void recuperarContrasena(String email) {
        Usuario usuario = buscarPorEmail(email);
        if (usuario == null) {
            return;
        }
        String claveTemp = generarClaveTemporal();
        usuario.setClave(claveTemp);
        usuarioRepositorio.save(usuario);
        enviarCorreoRecuperacion(usuario.getEmail(), usuario.getNombre(), claveTemp);
    }

    private String generarClaveTemporal() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder clave = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            clave.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return clave.toString();
    }

    private void enviarCorreoRecuperacion(String email, String nombre, String claveTemp) {
        try {
            MimeMessage mensaje = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");
            helper.setFrom("no-reply@mascotasapp.local");
            helper.setTo(email);
            helper.setSubject("Recuperacion de contraseña - CRUDL USUARIOS- MASCOTAS APP");
            String cuerpo = "<html><body style='font-family:Arial,sans-serif'>"
                    + "<h2>Hola, " + nombre + "</h2>"
                    + "<p>Tu nueva contraseña temporal es:</p>"
                    + "<h3>" + claveTemp + "</h3>"
                    + "<p>Por seguridad, cambiala al iniciar sesion.</p>"
                    + "</body></html>";
            helper.setText(cuerpo, true);
            mailSender.send(mensaje);
        } catch (Exception e) {
            System.err.println("Error enviando correo: " + e.getMessage());
        }
    }
}