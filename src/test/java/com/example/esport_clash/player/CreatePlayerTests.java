package com.example.esport_clash.player;

import com.example.esport_clash.player.application.usecases.CreatePlayerUseCase;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreatePlayerTests {
    @Test
    void shouldCreatePlayer () {
        var repository = new InMemoryPlayerRepository();
        var useCase = new CreatePlayerUseCase(repository);

        IdResponse response = useCase.execute("name");

        var expectedPlayer = new Player("id", "name");

        Player actualPlayer = repository.findById(response.getId());
        Assertions.assertEquals(actualPlayer.getName(), expectedPlayer.getName());
    }
}
