/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.misiontic.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author lobat
 */

@NoArgsConstructor
@Data
@Entity
@Table (name="reservas")
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @Column(nullable = false, length = 100)
    private String hotel;
    
    @NotEmpty
    @Column(nullable= false, length = 100)
    private String huesped;
    
    @NotEmpty
    @Column(nullable = false, length = 100)
    private LocalDate fechaIncio;
    
    @NotEmpty
    @Positive
    @Column(nullable = false, length = 100)
    private double valor;
    
    @NotEmpty
    @Column(nullable = false, length =50)
    private String habitacion;
    
    @NotEmpty
    @Column(nullable = false)
    private int numAcompaniantes;
    
    @NotEmpty
    @Column(nullable = false, length = 100)
    private LocalDate checkIn;
    
    
    @Column()
    private LocalDate checkOut;
    
    @NotEmpty
    @Column(nullable = false, length = 100)
    private String empleadoAtiende;
    
    
    @Column( length = 100)
    private String empleadoDespide;
    
    @NotEmpty
    @Column(nullable = false, length = 100)
    private String descripcion;
    
}
