package com.example.esport_clash.team.domain.model;

import com.example.esport_clash.core.domain.model.BaseEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Team extends BaseEntity<Team> {
    private String name;
    private Set<TeamMember> members;

    public Team() {}

    public Team(String id, String name) {
        super(id);
        this.name = name;
        this.members = new HashSet<TeamMember>();
    }

    private Team(String id, String name, Set<TeamMember> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    @Override
    public Team deepClone() {
        return new Team(
                this.id,
                this.name,
                this.members.stream().map(TeamMember::deepClone).collect(Collectors.toSet())
        );
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


    public class TeamMember extends BaseEntity<TeamMember>{
        private String playerId;
        private Role role;

        public TeamMember() {}

        private TeamMember (String id, String playerId, Role role) {
            super(id);
            this.playerId = playerId;
            this.role = role;
        }

        @Override
        public TeamMember deepClone() {
            return new TeamMember(this.id, this.playerId, this.role);
        }

        public String getPlayerId() {
            return playerId;
        }

        public Role getRole() {
            return role;
        }
    }
}
