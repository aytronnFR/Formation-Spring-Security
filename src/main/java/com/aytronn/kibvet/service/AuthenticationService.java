package com.aytronn.kibvet.service;

import com.aytronn.kibvet.dao.LocalUser;
import com.aytronn.kibvet.dto.AuthenticationRequest;
import com.aytronn.kibvet.dto.CreateUserRequest;
import com.aytronn.kibvet.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(
                                 UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LocalUser register(CreateUserRequest createUserRequest) {
        return null;
    }

    public AuthenticationRequest login(CreateUserRequest request) {
        return null;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }
}
