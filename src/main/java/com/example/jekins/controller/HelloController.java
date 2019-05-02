package com.example.jekins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String test(){
        return "请求接口为/";
    }

    @GetMapping("/index")
    public String index(){
        return "Welcome To Index Page !";
    }
}
