package com.example.prueba_seguridad.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR','BODEMA')")
    @GetMapping("/ver")
    public String ver(){

        return "Ver";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BODEGA')")
    @GetMapping("/modificar")
    public String modificar(){

        return "Modificar";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/eliminar")
    public String eliminar(){

        return "Eliminar";
    }

}
