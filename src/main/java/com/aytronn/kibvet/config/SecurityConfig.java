package com.aytronn.kibvet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aytronn.kibvet.enums.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/kibble/**").hasRole(Role.ADMIN.name())
                    .requestMatchers("/api/v1/kibble/**").permitAll()
                    .requestMatchers("/api/v1/test/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated();
        });
        return http.build();
    }
}
