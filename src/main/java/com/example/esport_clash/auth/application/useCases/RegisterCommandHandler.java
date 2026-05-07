package com.example.esport_clash.auth.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.application.services.passwordHasher.PasswordHasher;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.player.domain.viewModel.IdResponse;

import java.util.UUID;

public class RegisterCommandHandler implements Command.Handler<RegisterCommand, IdResponse> {
    private final UserRepository repository;
    private final PasswordHasher hasher;

    public RegisterCommandHandler(final UserRepository repository, final PasswordHasher hasher) {
        this.repository = repository;
        this.hasher = hasher;
    }

    @Override
    public IdResponse handle(RegisterCommand command) {
        var isEmailAvailable = this.repository.isEmailAddressAvailable(command.getEmail());

        if(!isEmailAvailable) {
            throw new IllegalArgumentException("Email is already is use");
        }

        var user = new User(
                UUID.randomUUID().toString(),
                command.getEmail(),
                hasher.hash(command.getPassword())
        );
        this.repository.save(user);

        return new IdResponse(user.getId());
    }
}
