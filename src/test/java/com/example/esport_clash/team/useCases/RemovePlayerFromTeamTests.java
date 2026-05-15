package com.example.esport_clash.team.useCases;

import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.team.application.ports.TeamRepository;
import com.example.esport_clash.team.application.useCases.RemovePlayerFromTeamCommand;
import com.example.esport_clash.team.application.useCases.RemovePlayerFromTeamCommandHandler;
import com.example.esport_clash.team.domain.model.Role;
import com.example.esport_clash.team.domain.model.Team;
import com.example.esport_clash.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RemovePlayerFromTeamTests {
    TeamRepository teamRepository = new InMemoryTeamRepository();
    Team team;
    String playerId = "Player1";
    Role role = Role.TOP;

    public RemovePlayerFromTeamCommandHandler commandHandler () {
        return new RemovePlayerFromTeamCommandHandler(teamRepository);
    }


    @BeforeEach
    void setUp() {
        teamRepository.clear();

        team = new Team("1", "Team1");
        team.addMember(playerId, role);

        teamRepository.save(team);
    }
    @Test
    void shouldRemovePlayerFromTeam() {
        var command = new RemovePlayerFromTeamCommand(playerId, team.getId());
        var commandHandler = commandHandler();

        commandHandler.handle(command);

        var expectedTeam = teamRepository.findById(team.getId()).get();
        Assertions.assertFalse(expectedTeam.hasMember(playerId, role));
    }

    @Test
    void whenTeamDoNotExist_ShouldThrow() {
        var command = new RemovePlayerFromTeamCommand(playerId, "garbage");
        var commandHandler = commandHandler();

      Assertions.assertThrows(
              NotFoundException.class,
              ()-> commandHandler.handle(command)
      );
    }
}
