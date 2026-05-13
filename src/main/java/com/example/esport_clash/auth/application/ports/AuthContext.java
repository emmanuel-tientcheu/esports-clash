package com.example.esport_clash.auth.application.ports;

import com.example.esport_clash.auth.domain.model.AuthUser;

import java.util.Optional;

public interface AuthContext {
    public boolean isAuthenticated();
    public Optional<AuthUser> getUser();
}
