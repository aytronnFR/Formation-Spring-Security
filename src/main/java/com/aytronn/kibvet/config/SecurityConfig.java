package main.java.com.aytronn.kibvet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Configuration de l'authentification
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // Définir les utilisateurs et rôles
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
                .and()
                .withUser("editor").password(passwordEncoder().encode("password")).roles("EDITOR")
                .and()
                .withUser("admin").password(passwordEncoder().encode("password")).roles("ADMIN");
                
    }

    // Configuration de l'autorisation
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/user").hasAnyRole("USER", "EDITOR")
                .antMatchers(HttpMethod.POST, "/api/v1/user").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/api/v1/user").hasRole("EDITOR")
                .antMatchers(HttpMethod.PUT, "/api/v1/user").hasRole("EDITOR")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    // Encoder de mot de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
