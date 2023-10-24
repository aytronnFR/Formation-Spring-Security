package com.aytronn.kibvet.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String getTest() {
        return "GET:: admin controller";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String post() {
        return "POST:: admin controller";
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String delete() {
        return "DELETE:: admin controller";
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String put() {
        return "PUT:: admin controller";
    }
}