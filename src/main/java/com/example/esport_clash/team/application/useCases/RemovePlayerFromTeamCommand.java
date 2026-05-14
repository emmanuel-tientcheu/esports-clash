package com.example.esport_clash.team.application.useCases;

import an.awesome.pipelinr.Command;

public class RemovePlayerFromTeamCommand implements Command<Void> {
    private String playerId;
    private String teamId;

    public RemovePlayerFromTeamCommand() {}

    public RemovePlayerFromTeamCommand(String playerId, String teamId) {
        this.playerId = playerId;
        this.teamId = teamId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getTeamId() {
        return teamId;
    }
}
