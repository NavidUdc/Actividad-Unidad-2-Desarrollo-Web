package com.misiontic.repositorio;

import com.misiontic.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, String> {
    // Spring Data genera el CRUD
    // La primary key es String (cedula)
    // Buscar usuario por email para recuperacion de contraseña
    Optional<Usuario> findByEmail(String email);
}
