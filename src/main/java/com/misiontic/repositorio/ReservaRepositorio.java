/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.misiontic.repositorio;

import com.misiontic.modelo.Reserva;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lobat
 */
@Repository
public interface ReservaRepositorio extends CrudRepository<Reserva, Long>{
    //Para que el mismo SpringData generte el CRUD
}
