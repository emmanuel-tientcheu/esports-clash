package com.example.esport_clash.team.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.team.application.ports.TeamRepository;

public class RemovePlayerFromTeamCommandHandler implements Command.Handler<RemovePlayerFromTeamCommand, Void> {
    private final TeamRepository teamRepository;

    public RemovePlayerFromTeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Void handle(RemovePlayerFromTeamCommand command) {
        var team = this.teamRepository.findById(command.getTeamId()).orElseThrow(
                () -> new NotFoundException("Team", command.getTeamId())
        );

        team.removeMember(command.getPlayerId());

        this.teamRepository.save(team);

        return null;
    }
}
