package com.example.esport_clash.team.application.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.team.domain.model.Role;

public class AddPlayerToTeamCommand implements Command<Void> {
    private String playerId;
    private String teamId;
    private Role role;

    public AddPlayerToTeamCommand() {}

    public AddPlayerToTeamCommand(String playerId, String teamId, Role role) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.role = role;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getTeamId() {
        return teamId;
    }

    public Role getRole() {
        return role;
    }
}
