package com.example.esport_clash.auth.infrastructure.auth.spring;

import com.example.esport_clash.auth.application.ports.AuthContext;
import com.example.esport_clash.auth.domain.model.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringAuthContext implements AuthContext {
    @Override
    public boolean isAuthenticated() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .isAuthenticated();
    }

    @Override
    public Optional<AuthUser> getUser() {
        return Optional.ofNullable(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        )
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof AuthUser) {
                        return (AuthUser) authentication.getPrincipal();
                    }
                    return null;
                });
    }
}
