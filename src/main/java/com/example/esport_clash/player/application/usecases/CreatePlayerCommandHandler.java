package com.example.esport_clash.player.application.usecases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.player.domain.viewModel.IdResponse;

import java.util.UUID;

public class CreatePlayerCommandHandler implements Command.Handler<CreatePlayerCommand, IdResponse> {
    private final PlayerRepository repository;

    public CreatePlayerCommandHandler(final PlayerRepository repository) {
        this.repository = repository;
    }

    public IdResponse handle(CreatePlayerCommand command) {
        final Player player = new Player(UUID.randomUUID().toString(), command.getName());
        this.repository.save(player);

        return new IdResponse(player.getId());
    }
}
