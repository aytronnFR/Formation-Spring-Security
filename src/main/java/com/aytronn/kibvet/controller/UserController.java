package com.aytronn.kibvet.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/api/v1/user")
    @PreAuthorize("hadAuthority('user:get','editor:get')")
    public String getTest() {
        return "GET:: user controller";
    }

    @PostMapping("/api/v1/user")
    @PreAuthorize("hadAuthority('editor:post')")
    public String post() {
        return "POST:: user controller";
    }

    @DeleteMapping("/api/v1/user")
    @PreAuthorize("hadAuthority('editor:delete')")
    public String delete() {
        return "DELETE:: user controller";
    }

    @PutMapping("/api/v1/user")
    @PreAuthorize("hadAuthority('editor:put')")
    public String put() {
        return "PUT:: user controller";
    }
}
