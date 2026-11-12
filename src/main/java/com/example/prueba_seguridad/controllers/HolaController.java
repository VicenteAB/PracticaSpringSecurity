package com.example.prueba_seguridad.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hola")
public class HolaController {

    @GetMapping("/mensaje")
    public String devolverTexto(){

        String texto = "Hola mundooo";
        System.out.println(texto);

        return texto;
    }

}
