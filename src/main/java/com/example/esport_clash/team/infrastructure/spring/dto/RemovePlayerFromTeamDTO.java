package com.example.esport_clash.team.infrastructure.spring.dto;

import com.example.esport_clash.team.domain.model.Role;

public class RemovePlayerFromTeamDTO {

    private String playerId;
    private String teamId;

    public RemovePlayerFromTeamDTO() {}

    public RemovePlayerFromTeamDTO(String playerId, String teamId) {
        this.playerId = playerId;
        this.teamId = teamId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getPlayerId() {
        return playerId;
    }

}
