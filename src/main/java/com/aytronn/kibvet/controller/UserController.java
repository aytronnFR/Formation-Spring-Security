package com.aytronn.kibvet.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserController {


    // private  UserService service;


    // @GetMapping("/api/v1/user")
    // @PreAuthorize("hasAuthority('user:get')")
    // public List<LocalUser> getAllUser() {
    //     // Obtenenir la liste des utilisateurs à partir de la source de données
    //     List<LocalUser> userList = service.getAllUser(); 
    
    //     // Retourner la liste des utilisateurs
    //     return userList;
    // }


    @GetMapping("/api/v1/user")
    @PreAuthorize("hasAuthority('user:get')")
    public String getTest() {
        return "GET:: user controller";
    }

    @PostMapping("/api/v1/user")
    @PreAuthorize("hasAuthority('user:post')")
    public String post() {
        return "POST:: user controller";
    }

    @DeleteMapping("/api/v1/user")
    @PreAuthorize("hasAuthority('user:delete')")
    public String delete() {
        return "DELETE:: user controller";
    }

    @PutMapping("/api/v1/user")
    @PreAuthorize("hasAuthority('user:put')")
    public String put() {
        return "PUT:: user controller";
    }
}
