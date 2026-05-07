package com.example.esport_clash.auth.application.services.passwordHasher;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordHasher implements PasswordHasher{
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public String hash(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean match(String clearPassword, String hashPassword) {
        return encoder.matches(clearPassword, hashPassword);
    }
}
