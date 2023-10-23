package com.aytronn.kibvet.config;

import com.aytronn.kibvet.repository.UserRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KibVetConfig {

    private final UserRepository userRepository;

    public KibVetConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
