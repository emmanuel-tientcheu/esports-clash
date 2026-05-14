package com.example.esport_clash.auth.infrastructure.spring;


import com.example.esport_clash.auth.application.ports.AuthContext;
import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.infrastructure.auth.spring.SpringAuthContext;
import com.example.esport_clash.auth.infrastructure.persistance.ram.InMemoryUserRepository;
import com.example.esport_clash.auth.infrastructure.persistance.sql.SQLUserAccessor;
import com.example.esport_clash.auth.infrastructure.persistance.sql.SQLUserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthAdapterConfiguration {
    @Bean
    public UserRepository userRepository(EntityManager entityManager,  SQLUserAccessor userAccessor) {
        return new SQLUserRepository(entityManager, userAccessor);
    }

    @Bean
    public AuthContext authContext() {
        return new SpringAuthContext();
    }
}
