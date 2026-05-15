package com.example.esport_clash.team.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.team.application.ports.TeamRepository;

public class DeleteTeamCommandHandler implements Command.Handler<DeleteTeamCommand, Void> {
    private final TeamRepository repository;

    public DeleteTeamCommandHandler(TeamRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(DeleteTeamCommand command) {
        var team = this.repository.findById(command.getId()).orElseThrow(
                () -> new NotFoundException("Team", command.getId())
        );
        this.repository.delete(team);
        return null;
    }
}
