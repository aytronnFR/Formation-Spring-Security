package com.aytronn.kibvet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationRequest(
        @JsonProperty("access_token")
        String accessToken
) {
}