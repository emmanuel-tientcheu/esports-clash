package com.example.esport_clash.player;

import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.application.usecases.ChangePlayerNameCommand;
import com.example.esport_clash.player.application.usecases.ChangePlayerNameCommandHandler;
import com.example.esport_clash.player.application.usecases.DeletePlayerCommand;
import com.example.esport_clash.player.application.usecases.DeletePlayerCommandHandler;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeletePlayerTest {
    private final Player player = new Player("123" , "old name");
    private final PlayerRepository repository = new InMemoryPlayerRepository();

    private DeletePlayerCommandHandler deletePlayerCommandHandler() {
        return new DeletePlayerCommandHandler(repository);
    }

    @Test
    void shouldRenamePlayer() {
        var useCase = deletePlayerCommandHandler();

        repository.save(player);
        var command = new DeletePlayerCommand(player.getId());
        useCase.handle(command);

        var expectedPlayerQuery = repository.findById(player.getId());

        Assertions.assertTrue(expectedPlayerQuery.isEmpty());

    }

    @Test
    void whenPlayerDoesNotExist_shouldThrowNotFoundException() {
        var useCase = deletePlayerCommandHandler();

        var command = new DeletePlayerCommand("garbage");

        var result = Assertions.assertThrows(
                NotFoundException.class,
                ()-> useCase.handle(command)
        );

        Assertions.assertEquals(result.getMessage(), "Player with the key garbage was not found");
    }
}
