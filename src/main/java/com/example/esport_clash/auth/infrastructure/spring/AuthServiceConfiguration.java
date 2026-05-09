package com.example.esport_clash.auth.infrastructure.spring;

import com.example.esport_clash.auth.application.services.passwordHasher.BcryptPasswordHasher;
import com.example.esport_clash.auth.application.services.passwordHasher.PasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthServiceConfiguration {
    @Bean
    public PasswordHasher passwordHasher() {
        return new BcryptPasswordHasher();
    }
}
