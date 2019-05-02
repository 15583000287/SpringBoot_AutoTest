package com.example.jekins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/index")
    public String index(){
        return "Welcome To Index Page !";
    }
}
