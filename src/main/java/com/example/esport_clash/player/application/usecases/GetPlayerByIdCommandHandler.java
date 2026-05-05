package com.example.esport_clash.player.application.usecases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.viewModel.PlayerViewResponse;

public class GetPlayerByIdCommandHandler implements Command.Handler<GetPlayerByIdCommand, PlayerViewResponse> {
    private final PlayerRepository repository;

    public GetPlayerByIdCommandHandler(PlayerRepository repository) {
        this.repository = repository;
    }
    @Override
    public PlayerViewResponse handle(GetPlayerByIdCommand command) {
        var player = this.repository.findById(command.getId()).orElseThrow(
                ()-> new NotFoundException("Player", command.getId())
        );
        return new PlayerViewResponse(player.getId(), player.getName());
    }
}
