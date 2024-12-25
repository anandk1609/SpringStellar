package com.beginner.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/chhaya")
    public String getNugget() {
        return "Chhaya Tundwal is a heartless bitch";
    }
}
