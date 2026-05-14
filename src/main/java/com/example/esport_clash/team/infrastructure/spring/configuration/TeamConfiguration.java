package com.example.esport_clash.team.infrastructure.spring.configuration;

import com.example.esport_clash.team.application.services.TeamRepository;
import com.example.esport_clash.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamConfiguration {
    @Bean
    public TeamRepository teamRepository() {
        return new InMemoryTeamRepository();
    }
}
