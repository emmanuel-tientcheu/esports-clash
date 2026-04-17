package com.example.esport_clash.player.infrastructure.spring;

import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.infrastructure.persistance.jpa.SQLPlayerDataAccessor;
import com.example.esport_clash.player.infrastructure.persistance.jpa.SQLPlayerRepository;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfiguration {
    @Bean
    public PlayerRepository playerRepository(final SQLPlayerDataAccessor dataAccessor) {
        return new SQLPlayerRepository(dataAccessor);
    }
}

