package com.example.esport_clash.team.infrastructure.persistance.jpa;

import com.example.esport_clash.core.infrastructure.persistance.sql.SQLBaseRepository;
import com.example.esport_clash.team.application.ports.TeamRepository;
import com.example.esport_clash.team.domain.model.Team;
import jakarta.persistence.EntityManager;

public class SQLTeamRepository extends SQLBaseRepository<Team> implements TeamRepository {
    public SQLTeamRepository(EntityManager entity) {
        super(entity);
    }

    @Override
    public Class<Team> getEntityMangerClass() {
        return Team.class;
    }
}
