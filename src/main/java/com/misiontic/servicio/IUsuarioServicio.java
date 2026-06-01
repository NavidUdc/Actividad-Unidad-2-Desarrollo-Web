package com.misiontic.servicio;

import com.misiontic.modelo.Usuario;
import java.util.List;

public interface IUsuarioServicio {

    public List<Usuario> listarTodos();

    public void guardar(Usuario usuario);

    public void actualizar(Usuario usuario);

    public void eliminar(String cedula);

    public Usuario buscarPorCedula(String cedula);

    public Usuario buscarPorEmail(String email);

    public void recuperarContrasena(String email);
}
