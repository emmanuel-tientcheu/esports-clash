package com.example.esport_clash.auth.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.player.domain.viewModel.IdResponse;

import java.util.UUID;

public class RegisterCommandHandler implements Command.Handler<RegisterCommand, IdResponse> {
    private final UserRepository repository;

    public RegisterCommandHandler(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public IdResponse handle(RegisterCommand command) {
        var user = new User(
                UUID.randomUUID().toString(),
                command.getEmail(),
                command.getPassword()
        );
        this.repository.save(user);

        return new IdResponse(user.getId());
    }
}
