/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.misiontic.servicio;

import com.misiontic.modelo.Reserva;
import java.util.List;

/**
 *
 * @author lobat
 */
public interface IReservaServicio {
    
    public List<Reserva> listarTodas();
    public void guardar (Reserva reserva);
    public Reserva buscarPorId (Long id);
    public void actualizar (Reserva reservad);
    public void eliminar (Long id);

}
