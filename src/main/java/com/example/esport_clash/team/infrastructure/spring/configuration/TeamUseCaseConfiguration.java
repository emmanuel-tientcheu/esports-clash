package com.example.esport_clash.team.infrastructure.spring.configuration;

import com.example.esport_clash.team.application.services.TeamRepository;
import com.example.esport_clash.team.application.useCases.CreateTeamCommandHandler;
import com.example.esport_clash.team.application.useCases.DeleteTeamCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamUseCaseConfiguration {
    @Bean
    public CreateTeamCommandHandler createTeamUseCase(TeamRepository teamRepository) {
        return new CreateTeamCommandHandler(teamRepository);
    }

    @Bean
    public DeleteTeamCommandHandler deleteTeamUseCase(TeamRepository teamRepository) {
        return new DeleteTeamCommandHandler(teamRepository);
    }
}
