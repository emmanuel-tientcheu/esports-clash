package com.example.esport_clash.auth;

import com.example.esport_clash.auth.application.services.passwordHasher.BcryptPasswordHasher;
import com.example.esport_clash.auth.application.services.passwordHasher.PasswordHasher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordHasherTests {
    @Test
    void shouldEncodePassword() {
        String clearPassword = "password";
        PasswordHasher encode = new BcryptPasswordHasher();

        var hasPassword = encode.hash(clearPassword);

        Assertions.assertTrue(
                encode.match(clearPassword, hasPassword)
        );
    }
}
