package com.aytronn.kibvet.dto;

public record CreateUserRequest(
        String username,
        String password
) {
}
