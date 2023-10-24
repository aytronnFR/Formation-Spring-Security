package com.aytronn.kibvet.config;

import com.aytronn.kibvet.enums.Role;
import com.aytronn.kibvet.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(AuthenticationProvider provider, JwtTokenFilter jwtTokenFilter) {
        this.authenticationProvider = provider;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);

        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(authorizationManager -> {
            authorizationManager
                    // Auth Controller: Aucune restriction de sécurité
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    // Admin Controller: Accès limité au rôle ADMIN
                    .requestMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name())
                    // User Controller: Méthode GET: Accès limité au rôle USER
                    .requestMatchers(HttpMethod.GET, "/api/v1/user/**").hasRole(Role.USER.name())
                    // User Controller: Méthodes POST, DELETE, PUT: Accès limité au rôle EDITOR
                    .requestMatchers(HttpMethod.POST, "/api/v1/user/**").hasRole(Role.EDITOR.name())
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasRole(Role.EDITOR.name())
                    .requestMatchers(HttpMethod.PUT, "/api/v1/user/**").hasRole(Role.EDITOR.name())
                    .anyRequest().authenticated();
        });
        return http.build();
    }
}
