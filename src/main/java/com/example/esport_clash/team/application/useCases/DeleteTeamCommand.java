package com.example.esport_clash.team.application.useCases;

import an.awesome.pipelinr.Command;

public class DeleteTeamCommand implements Command<Void> {
    private String id;

    public DeleteTeamCommand() {}

    public DeleteTeamCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
