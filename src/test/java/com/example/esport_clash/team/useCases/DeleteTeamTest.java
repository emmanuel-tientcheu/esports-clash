package com.example.esport_clash.team.useCases;

import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.team.application.useCases.DeleteTeamCommand;
import com.example.esport_clash.team.application.useCases.DeleteTeamCommandHandler;
import com.example.esport_clash.team.domain.model.Team;
import com.example.esport_clash.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteTeamTest {

    InMemoryTeamRepository repository = new InMemoryTeamRepository();
    public DeleteTeamCommandHandler deleteTeamCommandHandler() {
        return new DeleteTeamCommandHandler(repository);
    }

    @Test
    void shouldDeleteTeam() {
        Team team = new Team("123", "first team");

        repository.save(team);
        var command = new DeleteTeamCommand(team.getId());
        var useCase = deleteTeamCommandHandler();

        useCase.handle(command);

        var expectedTeam = repository.findById(team.getId());
        Assertions.assertTrue(expectedTeam.isEmpty());
    }

    @Test
    void whenTeamDoNotExist_ShouldThrow() {

        var command = new DeleteTeamCommand("garbage");
        var useCase = deleteTeamCommandHandler();

        Assertions.assertThrows(
                NotFoundException.class,
                () ->  useCase.handle(command)
        );
    }
}
