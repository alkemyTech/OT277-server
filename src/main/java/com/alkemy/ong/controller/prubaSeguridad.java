package com.alkemy.ong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class prubaSeguridad {

    @GetMapping("/admin")
    public String prueba1(){
        return "Hola Admin";
    }

    @GetMapping("/user")
    public String prueba2(){
        return "Hola User";
    }
}
