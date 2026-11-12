package com.example.prueba_seguridad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("1234")) // Contraseña cifrada con el encoder del Paso 2
                .roles("ADMIN") // Sin el prefijo ROLE_
                .build();

        UserDetails vendedor = User.withUsername("vendedor")
                .password(passwordEncoder.encode("1234")) // Contraseña cifrada
                .roles("VENDEDOR") // Sin el prefijo ROLE_
                .build();

        return new InMemoryUserDetailsManager(admin, vendedor);
    }
}
