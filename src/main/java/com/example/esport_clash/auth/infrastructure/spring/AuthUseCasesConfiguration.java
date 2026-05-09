package com.example.esport_clash.auth.infrastructure.spring;

import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.application.services.passwordHasher.PasswordHasher;
import com.example.esport_clash.auth.application.useCases.RegisterCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCasesConfiguration {
    @Bean
    public RegisterCommandHandler registerUseCase(UserRepository repository, PasswordHasher passwordHasher) {
        return new RegisterCommandHandler(repository, passwordHasher);
    }
}
