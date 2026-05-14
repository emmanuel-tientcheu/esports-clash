package com.example.esport_clash.team.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.team.application.services.TeamRepository;

public class AddPlayerToTeamCommandHandler implements Command.Handler<AddPlayerToTeamCommand, Void> {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public AddPlayerToTeamCommandHandler(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }


    @Override
    public Void handle(AddPlayerToTeamCommand command) {
       var team = teamRepository.findById(command.getTeamId()).orElseThrow(
               () -> new NotFoundException("Team", command.getTeamId())
       );

        var player = playerRepository.findById(command.getPlayerId()).orElseThrow(
                () -> new NotFoundException("Player", command.getPlayerId())
        );

        team.addMember(player.getId(), command.getRole());

        teamRepository.save(team);

        return null;
    }
}
