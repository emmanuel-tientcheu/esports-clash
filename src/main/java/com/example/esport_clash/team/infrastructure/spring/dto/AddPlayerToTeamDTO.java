package com.example.esport_clash.team.infrastructure.spring.dto;

import com.example.esport_clash.team.domain.model.Role;

public class AddPlayerToTeamDTO {

    private String playerId;
    private String teamId;
    private String role;

    public AddPlayerToTeamDTO() {}

    public AddPlayerToTeamDTO(String playerId, String teamId, String role) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.role = role;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public Role getRole() {
        return Role.formString(role);
    }
}
