package com.example.esport_clash.team.infrastructure.spring.configuration;

import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.team.application.services.TeamRepository;
import com.example.esport_clash.team.application.useCases.AddPlayerToTeamCommandHandler;
import com.example.esport_clash.team.application.useCases.CreateTeamCommandHandler;
import com.example.esport_clash.team.application.useCases.DeleteTeamCommandHandler;
import com.example.esport_clash.team.application.useCases.RemovePlayerFromTeamCommandHandler;
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

    @Bean
    public AddPlayerToTeamCommandHandler addPlayerToTeamUseCase(PlayerRepository playerRepository, TeamRepository teamRepository) {
        return new AddPlayerToTeamCommandHandler(playerRepository, teamRepository);
    }

    @Bean
    public RemovePlayerFromTeamCommandHandler removePlayerFromTeamUseCase(TeamRepository teamRepository) {
        return new RemovePlayerFromTeamCommandHandler(teamRepository);
    }
}
