package com.example.esport_clash.player.application.usecases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.player.application.ports.PlayerRepository;

public class DeletePlayerCommandHandler implements Command.Handler<DeletePlayerCommand, Void> {
    private final PlayerRepository repository;

    public DeletePlayerCommandHandler(final PlayerRepository repository) { this.repository = repository; }

    @Override
    public Void handle(DeletePlayerCommand command) {
        var player = this.repository.findById(command.getId()).orElseThrow(
                ()-> new NotFoundException("Player", command.getId())
        );
        this.repository.delete(player);

        return null;
    }
}
