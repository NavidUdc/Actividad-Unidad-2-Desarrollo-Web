package com.misiontic.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotEmpty
    @Column(length = 20)
    private String cedula;

    @NotEmpty
    @Column(nullable = false, length = 255)
    private String clave;

    @NotEmpty
    @Column(nullable = false, length = 100)
    private String nombre;

    @Email
    @NotEmpty
    @Column(nullable = false, length = 150)
    private String email;
}