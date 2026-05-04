package com.example.esport_clash.player.application.usecases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.player.application.ports.PlayerRepository;

public class ChangePlayerNameCommandHandler implements Command.Handler<ChangePlayerNameCommand, Void> {
    private final PlayerRepository repository;

    public ChangePlayerNameCommandHandler(final PlayerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(ChangePlayerNameCommand command) {
        var playerQuery = this.repository.findById(command.getId());
        if(playerQuery.isEmpty()) {
            throw new NotFoundException("Player", command.getId());
        }
        var player = playerQuery.get();
        player.rename(command.getName());
        this.repository.save(player);
        return null;
    }
}
