package com.misiontic.seguridad;

import com.misiontic.modelo.Usuario;
import com.misiontic.repositorio.UsuarioRepositorio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class AutenticacionServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // Buscar usuario por cedula
        Optional<Usuario> porCedula =
                usuarioRepositorio.findById(username);

        if (porCedula.isPresent()) {

            Usuario usuario = porCedula.get();

            return User.builder()
                    .username(usuario.getNombre())
                    .password(usuario.getClave())
                    .roles("USER")
                    .build();
        }

        // Buscar usuario por email
        Optional<Usuario> porEmail =
                usuarioRepositorio.findByEmail(username);

        if (porEmail.isPresent()) {

            Usuario usuario = porEmail.get();

            return User.builder()
                    .username(usuario.getNombre())
                    .password(usuario.getClave())
                    .roles("USER")
                    .build();
        }

        throw new UsernameNotFoundException(
                "No se encontro usuario con cedula o email: " + username
        );
    }
}