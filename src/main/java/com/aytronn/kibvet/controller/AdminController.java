package com.aytronn.kibvet.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {


    @GetMapping("/api/v1/admin")
    public String getTest() {
        return "GET:: admin controller";
    }

    @PostMapping("/api/v1/admin")
    public String post() {
        return "POST:: admin controller";
    }

    @DeleteMapping("/api/v1/admin")
    public String delete() {
        return "DELETE:: admin controller";
    }

    @PutMapping("/api/v1/admin")
    public String put() {
        return "PUT:: admin controller";
    }
}
