package com.example.esport_clash.team.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.player.domain.viewModel.IdResponse;

public class CreateTeamCommand implements Command<IdResponse> {
    private String name;

    public CreateTeamCommand() {}

    public CreateTeamCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
