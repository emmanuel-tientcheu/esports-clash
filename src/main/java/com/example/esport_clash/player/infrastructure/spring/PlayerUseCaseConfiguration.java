package com.example.esport_clash.player.infrastructure.spring;

import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import com.example.esport_clash.player.application.usecases.CreatePlayerCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerUseCaseConfiguration {
    @Bean
    public CreatePlayerCommandHandler createPlayerUseCase(final InMemoryPlayerRepository playerRepository) {
        return new CreatePlayerCommandHandler(playerRepository);
    }
}
