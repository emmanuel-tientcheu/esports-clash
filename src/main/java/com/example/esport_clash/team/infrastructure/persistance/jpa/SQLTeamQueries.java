package com.example.esport_clash.team.infrastructure.persistance.jpa;

import com.example.esport_clash.team.application.ports.TeamQueries;
import com.example.esport_clash.team.domain.model.Team;
import com.example.esport_clash.team.domain.viewModel.TeamViewModel;
import jakarta.persistence.EntityManager;

public class SQLTeamQueries implements TeamQueries {
    private final EntityManager entityManager;

    public SQLTeamQueries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TeamViewModel getTeamById(String id) {
        var query = this.entityManager.createQuery(
                "SELECT DISTINCT t FROM Team t " +
                        "JOIN FETCH t.members m " +
                        "JOIN FETCH m.player " +
                        "WHERE t.id = :id", Team.class
        );

        query.setParameter("id", id);
        var result = query.getSingleResult();

        var players = result.getMembers().stream()
                .map(
                        member -> new TeamViewModel.TeamMember(
                                member.getId(),
                                member.getPlayer().getId(),
                                member.getPlayer().getName(),
                                member.getRole().toString()
                        )
                ).toList();

        return new TeamViewModel(
                result.getId(),
                result.getName(),
                players
        );
    }
}
