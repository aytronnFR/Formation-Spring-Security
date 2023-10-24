package com.aytronn.kibvet.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String getTest() {
        return "GET:: user controller";
    }

    @PostMapping
    @PreAuthorize("hasRole('EDITOR')")
    public String post() {
        return "POST:: user controller";
    }

    @DeleteMapping
    @PreAuthorize("hasRole('EDITOR')")
    public String delete() {
        return "DELETE:: user controller";
    }

    @PutMapping
    @PreAuthorize("hasRole('EDITOR')")
    public String put() {
        return "PUT:: user controller";
    }
}
