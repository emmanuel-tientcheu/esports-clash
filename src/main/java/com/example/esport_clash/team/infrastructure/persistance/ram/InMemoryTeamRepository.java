package com.example.esport_clash.team.infrastructure.persistance.ram;

import com.example.esport_clash.core.infrastructure.persistance.ram.InMemoryBaseRepository;
import com.example.esport_clash.team.application.ports.TeamRepository;
import com.example.esport_clash.team.domain.model.Team;

import java.util.Optional;

public class InMemoryTeamRepository extends InMemoryBaseRepository<Team> implements TeamRepository {
    @Override
    public Optional<Team> getTeamByPlayerId(String id) {
        return this.entities.values()
                .stream()
                .filter(team -> team.getMembers()
                        .stream().anyMatch(teamMember -> teamMember.getPlayerId().equals(id)))
                .findFirst();
    }
}
