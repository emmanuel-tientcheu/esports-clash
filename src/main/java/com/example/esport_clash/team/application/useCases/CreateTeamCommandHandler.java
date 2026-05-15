package com.example.esport_clash.team.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import com.example.esport_clash.team.application.ports.TeamRepository;
import com.example.esport_clash.team.domain.model.Team;

import java.util.UUID;

public class CreateTeamCommandHandler implements Command.Handler<CreateTeamCommand, IdResponse> {
    private final TeamRepository repository;

    public CreateTeamCommandHandler(final TeamRepository repository) { this.repository = repository; }
    @Override
    public IdResponse handle(CreateTeamCommand command) {
        var team = new Team(
                UUID.randomUUID().toString(),
                command.getName()
        );

        this.repository.save(team);
        return new IdResponse(team.getId());
    }
}
