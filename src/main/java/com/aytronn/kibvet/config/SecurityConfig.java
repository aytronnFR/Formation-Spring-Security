package com.aytronn.kibvet.config;

import com.aytronn.kibvet.dao.LocalUser;
import com.aytronn.kibvet.enums.Role;
import com.aytronn.kibvet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Optional;

import static org.springframework.security.config.Customizer.withDefaults;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
   

    public SecurityConfig(AuthenticationProvider provider) {
        this.authenticationProvider = provider;
    }


    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);

        http.authenticationProvider(authenticationProvider);
      

        http.authorizeHttpRequests(authorizationManager -> {
            authorizationManager
                .requestMatchers(HttpMethod.GET, "/api/v1/user").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/v1/user").hasRole("EDITOR")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/user").hasRole("EDITOR")
                .requestMatchers(HttpMethod.PUT, "/api/v1/user").hasRole("EDITOR")
                .requestMatchers(HttpMethod.GET, "/api/v1/admin/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/api/v1/admin/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated();
        });
        return http.build();
    }
}
