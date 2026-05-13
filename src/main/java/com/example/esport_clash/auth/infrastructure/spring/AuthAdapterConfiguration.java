package com.example.esport_clash.auth.infrastructure.spring;


import com.example.esport_clash.auth.application.ports.AuthContext;
import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.infrastructure.auth.spring.SpringAuthContext;
import com.example.esport_clash.auth.infrastructure.persistance.ram.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthAdapterConfiguration {
    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public AuthContext authContext() {
        return new SpringAuthContext();
    }
}
