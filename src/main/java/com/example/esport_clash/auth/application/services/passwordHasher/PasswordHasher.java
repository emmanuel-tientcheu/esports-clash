package com.example.esport_clash.auth.application.services.passwordHasher;

public interface PasswordHasher {
    public String hash(String password);
    public boolean match(String clearPassword, String hashPassword);
}
