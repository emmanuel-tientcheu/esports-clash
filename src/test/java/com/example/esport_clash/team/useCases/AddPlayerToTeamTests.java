package com.example.esport_clash.team.useCases;

import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import com.example.esport_clash.team.application.services.TeamRepository;
import com.example.esport_clash.team.application.useCases.AddPlayerToTeamCommand;
import com.example.esport_clash.team.application.useCases.AddPlayerToTeamCommandHandler;
import com.example.esport_clash.team.domain.model.Role;
import com.example.esport_clash.team.domain.model.Team;
import com.example.esport_clash.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddPlayerToTeamTests {
    PlayerRepository playerRepository = new InMemoryPlayerRepository();
    Player player = new Player("1", "emma");

    TeamRepository teamRepository = new InMemoryTeamRepository();
    Team team = new Team("1", "team1");

    public AddPlayerToTeamCommandHandler addPlayerToTeamCommandHandler() {
        return new AddPlayerToTeamCommandHandler(playerRepository, teamRepository);
    }

    @BeforeEach
    void setUp() {
        teamRepository.clear();
        playerRepository.clear();

        teamRepository.save(team);
        playerRepository.save(player);

    }

    @Test
    void shouldAddPlayerToTeam() {

        var command = new AddPlayerToTeamCommand(player.getId(), team.getId(), Role.TOP);
        var useCase = addPlayerToTeamCommandHandler();

        useCase.handle(command);

        var expectedTeam = teamRepository.findById(team.getId()).get();

        Assertions.assertTrue(expectedTeam.hasMember(command.getPlayerId(), command.getRole()));
    }

    @Test
    void whenPlayerDoNotExist_ShouldThrow() {

        var command = new AddPlayerToTeamCommand("garbage", team.getId(), Role.TOP);
        var useCase = addPlayerToTeamCommandHandler();

       var exception = Assertions.assertThrows(
               NotFoundException.class,
               () -> useCase.handle(command)
       );

       Assertions.assertEquals("Player with the key garbage was not found", exception.getMessage());
    }

    @Test
    void whenTeamDoNotExist_ShouldThrow() {

        var command = new AddPlayerToTeamCommand(player.getId(),"garbage", Role.TOP);
        var useCase = addPlayerToTeamCommandHandler();

        var exception = Assertions.assertThrows(
                NotFoundException.class,
                () -> useCase.handle(command)
        );

        Assertions.assertEquals("Team with the key garbage was not found", exception.getMessage());
    }
}

