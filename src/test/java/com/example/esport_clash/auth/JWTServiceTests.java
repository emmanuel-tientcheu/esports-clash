package com.example.esport_clash.auth;

import com.example.esport_clash.auth.application.services.jwtservice.ConcreteJWTService;
import com.example.esport_clash.auth.application.services.jwtservice.JWTService;
import com.example.esport_clash.auth.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JWTServiceTests {
    @Test
    void shouldTokenizeUser() {
        User user = new User("123", "emmanuel@gmail.com", "password");
        JWTService service = new ConcreteJWTService("super_secret_key_please_dont_share", 60);

        var token = service.tokenize(user);
        var authUser = service.parse(token);

        Assertions.assertNotNull(authUser);

        Assertions.assertEquals(authUser.getEmail(), user.getEmail());
    }
}
