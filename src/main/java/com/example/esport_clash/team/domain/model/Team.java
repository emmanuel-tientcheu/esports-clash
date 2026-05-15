package com.example.esport_clash.team.domain.model;

import com.example.esport_clash.core.domain.model.BaseEntity;
import com.example.esport_clash.player.domain.model.Player;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity<Team> {
    @Column
    private String name;

    @OneToMany(
            mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<TeamMember> members;

    public Team() {}

    public Team(String id, String name) {
        super(id);
        this.name = name;
        this.members = new HashSet<TeamMember>();
    }

    private Team(String id, String name, Set<TeamMember> members) {
        super(id);
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
                this.id,
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

    public Set<TeamMember> getMembers() {
        return members;
    }

    @Entity
    @Table(name = "team_members")
    public static class TeamMember extends BaseEntity<TeamMember>{
        @Column(name = "player_id")
        private String playerId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "player_id", insertable = false, updatable = false)
        @MapsId("playerId")
        private Player player;

        @Column(name = "team_id")
        private String teamId;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "team_id", insertable = false, updatable = false)
        @MapsId("teamId")
        private Team team;

        @Column()
        @Enumerated(EnumType.STRING)
        private Role role;

        public TeamMember() {}

        private TeamMember (String id, String playerId, String teamId, Role role) {
            super(id);
            this.playerId = playerId;
            this.teamId = teamId;
            this.role = role;
        }

        @Override
        public TeamMember deepClone() {
            return new TeamMember(this.id, this.playerId, this.teamId, this.role);
        }

        public String getPlayerId() {
            return playerId;
        }

        public Player getPlayer() {
            return player;
        }

        public Role getRole() {
            return role;
        }
    }
}
