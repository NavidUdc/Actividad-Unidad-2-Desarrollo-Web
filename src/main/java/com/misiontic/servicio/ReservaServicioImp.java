/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.misiontic.servicio;

import com.misiontic.modelo.Reserva;
import com.misiontic.repositorio.ReservaRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author lobat
 */
@Service
public class ReservaServicioImp implements IReservaServicio{

    @Autowired
    private ReservaRepositorio reservaRepo;
            
    @Transactional(readOnly=true)
    @Override
    public List<Reserva> listarTodas() {
        return (List<Reserva>) reservaRepo.findAll();
    }

    @Transactional
    @Override
    public void guardar(Reserva reserva) {
        reservaRepo.save(reserva);
    }

    @Transactional(readOnly= true)
    @Override
    public Reserva buscarPorId(Long id) {
        Optional<Reserva> resultado = reservaRepo.findById(id);
        return resultado.orElse(null);
    }

    @Transactional
    @Override
    public void actualizar(Reserva reserva) {
        reservaRepo.save(reserva);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        reservaRepo.deleteById(id);
    }
    
}
