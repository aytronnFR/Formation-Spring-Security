package com.aytronn.kibvet.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @GetMapping("/api/v1/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getTest() {
        return "GET:: admin controller";
    }

    @PostMapping("/api/v1/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String post() {
        return "POST:: admin controller";
    }

    @DeleteMapping("/api/v1/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete() {
        return "DELETE:: admin controller";
    }

    @PutMapping("/api/v1/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String put() {
        return "PUT:: admin controller";
    }
}
