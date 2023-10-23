package com.aytronn.kibvet.controller;

import com.aytronn.kibvet.dao.LocalUser;
import com.aytronn.kibvet.dto.AuthenticationRequest;
import com.aytronn.kibvet.dto.CreateUserRequest;
import com.aytronn.kibvet.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public LocalUser register(@RequestBody CreateUserRequest createUserRequest) {
        return getAuthenticationService().register(createUserRequest);

    }

    @PostMapping("/login")
    public AuthenticationRequest login(@RequestBody CreateUserRequest createUserRequest) {
        return getAuthenticationService().login(createUserRequest);
    }

    public AuthenticationService getAuthenticationService() {
        return this.authenticationService;
    }

}
