package com.example.esport_clash.player;

import com.example.esport_clash.player.application.usecases.CreatePlayerCommand;
import com.example.esport_clash.player.application.usecases.CreatePlayerCommandHandler;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreatePlayerTests {
    @Test
    void shouldCreatePlayer () {
        var repository = new InMemoryPlayerRepository();
        var useCase = new CreatePlayerCommandHandler(repository);

        var command = new CreatePlayerCommand("name");

        IdResponse response = useCase.handle(command);

        var expectedPlayer = new Player("id", "name");

        Player actualPlayer = repository.findById(response.getId());
        Assertions.assertEquals(actualPlayer.getName(), expectedPlayer.getName());
    }
}
