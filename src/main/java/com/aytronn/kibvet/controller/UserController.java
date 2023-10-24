package com.aytronn.kibvet.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class UserController {

    @GetMapping("/api/v1/user")
    @PreAuthorize("hasAnyRole('USER', 'EDITOR')")
    public String getTest() {
        return "GET:: user controller";
    }

    @PostMapping("/api/v1/user")
    @PreAuthorize("hasRole('USER')")
    public String post() {
        return "POST:: user controller";
    }

    @DeleteMapping("/api/v1/user")
    @PreAuthorize("hasRole('EDITOR')")
    public String delete() {
        return "DELETE:: user controller";
    }

    @PutMapping("/api/v1/user")
    @PreAuthorize("hasRole('EDITOR')")
    public String put() {
        return "PUT:: user controller";
    }
}
