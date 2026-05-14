package com.example.esport_clash.team.useCases;

import com.example.esport_clash.player.domain.viewModel.IdResponse;
import com.example.esport_clash.team.application.useCases.CreateTeamCommand;
import com.example.esport_clash.team.application.useCases.CreateTeamCommandHandler;
import com.example.esport_clash.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateTeamTest {
    @Test
    void shouldCreatePlayer() {
        var repository = new InMemoryTeamRepository();
        var command = new CreateTeamCommand("first team");

        var useCase = new CreateTeamCommandHandler(repository);

        IdResponse response = useCase.handle(command);
        var expectedTeam = repository.findById(response.getId()).get();

        Assertions.assertEquals(expectedTeam.getName(), command.getName());
        Assertions.assertEquals(expectedTeam.getId(), response.getId());
    }
}
