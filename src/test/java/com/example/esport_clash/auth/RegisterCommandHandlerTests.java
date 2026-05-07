package com.example.esport_clash.auth;

import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.application.useCases.RegisterCommand;
import com.example.esport_clash.auth.application.useCases.RegisterCommandHandler;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.auth.infrastructure.persistance.ram.InMemoryUserRepository;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterCommandHandlerTests {
    private final UserRepository repository = new InMemoryUserRepository();

    public RegisterCommandHandler createCommandHandler() {
        return new RegisterCommandHandler(repository);
    }

    @Test
    void shouldCreateUser() {
        RegisterCommand command = new RegisterCommand("emmanuel@gmail.com", "password");
        RegisterCommandHandler useCase = createCommandHandler();

        IdResponse idResponse = useCase.handle(command);

        User user = repository.findById(idResponse.getId()).get();

        Assertions.assertEquals(user.getEmail(), command.getEmail());

    }
}
