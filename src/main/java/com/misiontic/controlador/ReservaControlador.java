/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.misiontic.controlador;

import com.misiontic.modelo.Reserva;
import com.misiontic.servicio.IReservaServicio;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lobat
 */

@Controller
@RequestMapping("/reservas")
public class ReservaControlador {
    @Autowired
    private IReservaServicio reservaServicio;

    // Listar Reservas
    @GetMapping
    public String listar(Model modelo) {

        List<Reserva> reservas = reservaServicio.listarTodas();

        modelo.addAttribute("reservas", reservas);

        return "reservas/lista";
    }

    // Formulario
    @GetMapping("/agregar")
    public String agregar(Reserva reserva) {

        return "reservas/formulario";
    }

    // Crear Reserva
    @PostMapping("/guardar")
    public String guardar(@Valid Reserva reserva,
                          Errors errores) {

        if (errores.hasErrors()) {
            return "reservas/formulario";
        }

        reservaServicio.guardar(reserva);

        return "redirect:/reservas";
    }

    // Mostrar detalles
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id,
                      Model modelo) {

        Reserva reserva = reservaServicio.buscarPorId(id);

        if (reserva == null) {
            return "redirect:/reservas";
        }

        modelo.addAttribute("reserva", reserva);

        return "reservas/detalle";
    }

    // Motstat formulario para actualizar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id,
                         Model modelo) {

        Reserva reserva = reservaServicio.buscarPorId(id);

        if (reserva == null) {
            return "redirect:/mascotas";
        }

        modelo.addAttribute("reserva", reserva);

        return "reservas/formulario";
    }

    // Actualizar
    @PostMapping("/actualizar")
    public String actualizar(@Valid Reserva reserva,
                             Errors errores) {

        if (errores.hasErrors()) {
            return "reservas/formulario";
        }

        reservaServicio.actualizar(reserva);

        return "redirect:/reservas";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {

        reservaServicio.eliminar(id);

        return "redirect:/reservas";
    }
}
