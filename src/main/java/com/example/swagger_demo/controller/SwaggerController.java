package com.example.swagger_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SwaggerController {

    @GetMapping
    public List<String> getAllUsers(){
        return List.of("Paras","Madhavi");
    }

    @PostMapping
    public String createUser() {
        return "User Created";
    }
}
