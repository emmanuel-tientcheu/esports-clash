package com.example.esport_clash.team.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.team.domain.viewModel.TeamViewModel;

public class GetTeamByIdCommand implements Command<TeamViewModel> {
    private String id;

    public GetTeamByIdCommand() {}

    public GetTeamByIdCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
