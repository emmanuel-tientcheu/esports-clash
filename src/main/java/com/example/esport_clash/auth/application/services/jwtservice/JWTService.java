package com.example.esport_clash.auth.application.services.jwtservice;

import com.example.esport_clash.auth.domain.model.AuthUser;
import com.example.esport_clash.auth.domain.model.User;

public interface JWTService {
    public String tokenize(User user);
    public AuthUser parse(String token);
}
