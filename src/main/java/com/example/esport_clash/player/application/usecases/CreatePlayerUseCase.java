package com.example.esport_clash.player.application.usecases;

import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.player.domain.viewModel.IdResponse;

import java.util.UUID;

public class CreatePlayerUseCase {
    private final InMemoryPlayerRepository repository;

    public CreatePlayerUseCase(final InMemoryPlayerRepository repository) {
        this.repository = repository;
    }

    public IdResponse execute(String name) {
        final Player player = new Player(UUID.randomUUID().toString(), name);
        this.repository.save(player);

        return new IdResponse(player.getId());
    }
}
