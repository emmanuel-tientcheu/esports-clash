package com.example.esport_clash.player.infrastructure.spring;

import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.application.usecases.ChangePlayerNameCommandHandler;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import com.example.esport_clash.player.application.usecases.CreatePlayerCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerUseCaseConfiguration {
    @Bean
    public CreatePlayerCommandHandler createPlayerUseCase(final PlayerRepository playerRepository) {
        return new CreatePlayerCommandHandler(playerRepository);
    }

    @Bean
    public ChangePlayerNameCommandHandler changePlayerNameUseCase(final PlayerRepository playerRepository) {
        return new ChangePlayerNameCommandHandler(playerRepository);
    }
}
