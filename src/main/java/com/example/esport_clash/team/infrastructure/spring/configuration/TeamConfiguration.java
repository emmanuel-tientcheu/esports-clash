package com.example.esport_clash.team.infrastructure.spring.configuration;

import com.example.esport_clash.team.application.ports.TeamQueries;
import com.example.esport_clash.team.application.ports.TeamRepository;
import com.example.esport_clash.team.infrastructure.persistance.jpa.SQLTeamQueries;
import com.example.esport_clash.team.infrastructure.persistance.jpa.SQLTeamRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamConfiguration {
    @Bean
    public TeamRepository teamRepository(EntityManager entityManager) {
        return new SQLTeamRepository(entityManager);
    }

    @Bean
    public TeamQueries teamQueries(EntityManager manager) {
        return new SQLTeamQueries(manager);
    }
}
