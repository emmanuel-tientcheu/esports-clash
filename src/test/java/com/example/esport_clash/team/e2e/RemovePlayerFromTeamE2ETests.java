package com.example.esport_clash.team.e2e;

import com.example.esport_clash.IntegrationTest;
import com.example.esport_clash.team.application.ports.TeamRepository;
import com.example.esport_clash.team.domain.model.Role;
import com.example.esport_clash.team.domain.model.Team;
import com.example.esport_clash.team.infrastructure.spring.dto.RemovePlayerFromTeamDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class RemovePlayerFromTeamE2ETests extends IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    TeamRepository teamRepository;

    String playerId = "Player1";
    Team team;
    Role role = Role.TOP;

    @BeforeEach
    public void setUp() {
        teamRepository.clear();

        team = new Team("123", "Team1");
        team.addMember(playerId, role);
        teamRepository.save(team);
    }

    @Test
    void shouldRemovePlayerFromTeam() throws Exception {
        var dto = new RemovePlayerFromTeamDTO(playerId, team.getId());
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/teams/remove-player-from-team")
                                .header("Authorization", createJWT())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

        var expectedTeams = teamRepository.findById(team.getId()).get();

        Assertions.assertFalse(expectedTeams.hasMember(playerId, Role.TOP));
    }
}
