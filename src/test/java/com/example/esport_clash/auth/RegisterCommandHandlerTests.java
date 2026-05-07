package com.example.esport_clash.auth;

import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.application.services.passwordHasher.BcryptPasswordHasher;
import com.example.esport_clash.auth.application.services.passwordHasher.PasswordHasher;
import com.example.esport_clash.auth.application.useCases.RegisterCommand;
import com.example.esport_clash.auth.application.useCases.RegisterCommandHandler;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.auth.infrastructure.persistance.ram.InMemoryUserRepository;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterCommandHandlerTests {
    private final InMemoryUserRepository repository = new InMemoryUserRepository();
    private final PasswordHasher hasher = new BcryptPasswordHasher();

    public RegisterCommandHandler createCommandHandler() {
        return new RegisterCommandHandler(repository,hasher);
    }

    @BeforeEach
    void setUp() {
        repository.clear();
    }

    @Test
    void shouldCreateUser() {
        RegisterCommand command = new RegisterCommand("emmanuel@gmail.com", "password");
        RegisterCommandHandler useCase = createCommandHandler();

        IdResponse idResponse = useCase.handle(command);

        User user = repository.findById(idResponse.getId()).get();

        Assertions.assertEquals(user.getEmail(), command.getEmail());
        Assertions.assertTrue(hasher.match(command.getPassword(), user.getPassword()));

    }

    @Test
    void whenEmailUse_ShouldThrows() {
        var currentUser = new User(
                "123",
                "emmanuel@gmail.com",
                "password"
        );

        repository.save(currentUser);
        RegisterCommand command = new RegisterCommand("emmanuel@gmail.com", "password");
        RegisterCommandHandler useCase = createCommandHandler();

       Assertions.assertThrows(
               IllegalArgumentException.class,
               () -> useCase.handle(command)
       );

    }
}
