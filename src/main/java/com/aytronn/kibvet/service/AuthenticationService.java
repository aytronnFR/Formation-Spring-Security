package com.aytronn.kibvet.service;

import com.aytronn.kibvet.config.JwtService;
import com.aytronn.kibvet.dao.LocalUser;
import com.aytronn.kibvet.dto.AuthenticationRequest;
import com.aytronn.kibvet.dto.CreateUserRequest;
import com.aytronn.kibvet.enums.Role;
import com.aytronn.kibvet.exception.UnauthorizedException;
import com.aytronn.kibvet.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(PasswordEncoder passwordEncoder,
                                 UserRepository userRepository,
                                 AuthenticationManager authenticationManager,
                                 JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LocalUser register(CreateUserRequest createUserRequest) {
        final LocalUser user = LocalUser.builder()
                .username(createUserRequest.username())
                .password(getPasswordEncoder().encode(createUserRequest.password()))
                .enabled(true)
                .role(Role.USER)
                .build();

        return getUserRepository().save(user);
    }

    public AuthenticationRequest login(CreateUserRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        final Optional<LocalUser> user = getUserRepository().findByUsername(request.username());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        final String accessToken = jwtService.generateToken(user.get());
        final String refreshToken = getJwtService().generateRefreshToken(user.get());

        return new AuthenticationRequest(accessToken, refreshToken);
    }

    public AuthenticationRequest refreshToken(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Bad token");
        }

        final String jwt = authHeader.substring(7);

        final String username = jwtService.getUsername(jwt);

        if (username == null) {
            throw new UnauthorizedException("Bad user");
        }

        Optional<LocalUser> byUsername = getUserRepository().findByUsername(username);

        if (byUsername.isEmpty()) {
            throw new UnauthorizedException("Bad user");
        }

        LocalUser user = byUsername.get();

        if (!getJwtService().isTokenValid(jwt, user)) {
            throw new UnauthorizedException("Bad token");
        }

        final String accessToken = jwtService.generateToken(user);
        final String refreshToken = getJwtService().generateRefreshToken(user);

        return new AuthenticationRequest(accessToken, refreshToken);
    }

    public PasswordEncoder getPasswordEncoder() {
        return this.passwordEncoder;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public AuthenticationManager getAuthenticationManager() {
        return this.authenticationManager;
    }

    public JwtService getJwtService() {
        return this.jwtService;
    }
}
