package com.example.esport_clash.player;

import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.application.usecases.ChangePlayerNameCommand;
import com.example.esport_clash.player.application.usecases.ChangePlayerNameCommandHandler;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RenamePlayerTest {
    private final Player player = new Player("123" , "old name");
    private final PlayerRepository repository = new InMemoryPlayerRepository();

    private ChangePlayerNameCommandHandler renamePlayerCommandHandler() {
        return new ChangePlayerNameCommandHandler(repository);
    }

    @Test
    void shouldRenamePlayer() {
        var useCase = renamePlayerCommandHandler();

        repository.save(player);
        var command = new ChangePlayerNameCommand(player.getId(), "new name");
        useCase.handle(command);

        var expectedPlayer = repository.findById(player.getId());

        Assertions.assertEquals(expectedPlayer.get().getName(), command.getName());

    }

    @Test
    void whenPlayerDoesNotExist_shouldThrowNotFoundException() {
        var useCase = new ChangePlayerNameCommandHandler(repository);

        var command = new ChangePlayerNameCommand("garbage", "new name");

        var result = Assertions.assertThrows(
                NotFoundException.class,
                ()-> useCase.handle(command)
        );

        Assertions.assertEquals(result.getMessage(), "Player with the key garbage was not found");
    }
}
