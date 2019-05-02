package com.example.jekins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JekinsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JekinsApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Welcome to Jenkins!  这是我使用Jenkins + Docker自动化部署的第一个应用！";
    }
}
