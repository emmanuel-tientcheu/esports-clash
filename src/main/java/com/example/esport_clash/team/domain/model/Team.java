package com.example.esport_clash.team.domain.model;

import com.example.esport_clash.core.domain.model.BaseEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Team extends BaseEntity {
    private String name;
    private Set<TeamMember> members;

    public Team() {}

    public Team(String id, String name) {
        super(id);
        this.name = name;
        this.members = new HashSet<TeamMember>();
    }

    public void addMember(String playerId, Role role) {
        if(this.members.stream().anyMatch(members -> members.getPlayerId().equals(playerId))) {
            throw new IllegalArgumentException("This player is already in the team");
        }

        if(this.members.stream().anyMatch(members -> members.role == role)) {
            throw new IllegalArgumentException("This role is already taken");
        }

        this.members.add(new TeamMember(
                UUID.randomUUID().toString(),
                playerId,
                role
        ));
    }

    public void removeMember(String playerId) {
        if(this.members.stream().noneMatch(player -> player.playerId.equals(playerId))) {
            throw new IllegalArgumentException("this player is not in the team");
        }
        this.members.removeIf(player -> player.playerId.equals(playerId));
    }

    public boolean hasMember(String playerId, Role role) {
        return this.members
                .stream()
                .anyMatch(members -> members.getPlayerId().equals(playerId) && members.role == role);

    }

    public String getName() {
        return name;
    }


    public class TeamMember {
        private String id;
        private String playerId;
        private Role role;

        public TeamMember() {}

        private TeamMember (String id, String playerId, Role role) {
            this.id = id;
            this.playerId = playerId;
            this.role = role;
        }

        public String getId() {
            return id;
        }

        public String getPlayerId() {
            return playerId;
        }

        public Role getRole() {
            return role;
        }
    }
}
