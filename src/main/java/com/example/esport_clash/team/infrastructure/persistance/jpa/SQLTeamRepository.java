package com.example.esport_clash.team.infrastructure.persistance.jpa;

import com.example.esport_clash.core.infrastructure.persistance.sql.SQLBaseRepository;
import com.example.esport_clash.team.application.ports.TeamRepository;
import com.example.esport_clash.team.domain.model.Team;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SQLTeamRepository extends SQLBaseRepository<Team> implements TeamRepository {
    public SQLTeamRepository(EntityManager entity) {
        super(entity);
    }

    @Override
    public Class<Team> getEntityMangerClass() {
        return Team.class;
    }

    @Override
    public Optional<Team> getTeamByPlayerId(String id) {
        var query = this.entityManager.createQuery(
                "SELECT t FROM Team t JOIN t.members m WHERE m.playerId = :playerId", Team.class
        );

        query.setParameter("playerId", id);

        return query
                .getResultList()
                .stream()
                .findFirst();
    }
}
