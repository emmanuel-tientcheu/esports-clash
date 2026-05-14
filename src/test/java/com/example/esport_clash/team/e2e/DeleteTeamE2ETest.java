package com.example.esport_clash.team.e2e;

import com.example.esport_clash.IntegrationTest;
import com.example.esport_clash.team.application.services.TeamRepository;
import com.example.esport_clash.team.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class DeleteTeamE2ETest extends IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    TeamRepository repository;

    @Test
    void shouldDeleteTeam() throws Exception {
        var team = new Team("123", "first team");
        repository.save(team);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/teams/" + team.getId())
                        .header("Authorization", createJWT())
        )
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        var expectedTeam = repository.findById(team.getId());
        Assertions.assertTrue(expectedTeam.isEmpty());
    }
}
