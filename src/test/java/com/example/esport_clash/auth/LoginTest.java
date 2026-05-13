package com.example.esport_clash.auth;

import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.application.services.jwtservice.ConcreteJWTService;
import com.example.esport_clash.auth.application.services.jwtservice.JWTService;
import com.example.esport_clash.auth.application.services.passwordHasher.BcryptPasswordHasher;
import com.example.esport_clash.auth.application.services.passwordHasher.PasswordHasher;
import com.example.esport_clash.auth.application.useCases.LoginCommand;
import com.example.esport_clash.auth.application.useCases.LoginCommandHandler;
import com.example.esport_clash.auth.domain.model.AuthUser;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.auth.infrastructure.persistance.ram.InMemoryUserRepository;
import com.example.esport_clash.core.domain.exceptions.BadRequestException;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.token.TokenService;

public class LoginTest {
    private final UserRepository repository = new InMemoryUserRepository();
    private final JWTService tokenService = new ConcreteJWTService("secret_key_please_change_this_later_is_not_sure", 60);
    private final PasswordHasher hasher = new BcryptPasswordHasher();
    User user = new User("123", "emmanuel@gmail.com", hasher.hash("password"));

    private LoginCommandHandler commandHandler() {
        return new LoginCommandHandler(repository, tokenService, hasher);
    }

    @BeforeEach
    void setUp() {
        repository.clear();

        repository.save(user);
    }

    @Nested
    class Scenario_HappyPath {
        @Test
        void shouldReturnUser() {
            repository.save(user);

            var command = new LoginCommand("emmanuel@gmail.com", "password");
            var useCase = commandHandler();

            var result = useCase.handle(command);

            Assertions.assertEquals(user.getId(), result.getId());
            Assertions.assertEquals(user.getEmail(), result.getEmail());

            AuthUser authUser = tokenService.parse(result.getToken());
            Assertions.assertEquals(authUser.getEmail(), result.getEmail());
        }
    }

    @Nested
    class Scenario_TheEmailIsIncorrect {
        @Test
        void shouldThrowNotFound() {
            var command = new LoginCommand("notfound@gmail.com", "password");
            var useCase = commandHandler();

            Assertions.assertThrows(
                    NotFoundException.class,
                    ()-> useCase.handle(command)
            );
        }

    }

    @Nested
    class Scenario_ThePasswordIsIncorrect {
        @Test
        void shouldThrowBadRequest() {
            var command = new LoginCommand("emmanuel@gmail.com", "bad password");
            var useCase = commandHandler();

            Assertions.assertThrows(
                    BadRequestException.class,
                    ()-> useCase.handle(command)
            );
        }
    }
}
