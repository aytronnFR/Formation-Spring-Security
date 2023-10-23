package com.aytronn.kibvet.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // Méthode GET: Accès limité au rôle USER.
    //Méthodes POST, DELETE, PUT: Accès limité au rôle EDITOR.

    
    @GetMapping("/api/v1/user")
    public String getTest() {
        return "GET:: user controller";
    }

    @PostMapping("/api/v1/user")
    public String post() {
        return "POST:: user controller";
    }

    @DeleteMapping("/api/v1/user")
    public String delete() {
        return "DELETE:: user controller";
    }

    @PutMapping("/api/v1/user")
    public String put() {
        return "PUT:: user controller";
    }
}
