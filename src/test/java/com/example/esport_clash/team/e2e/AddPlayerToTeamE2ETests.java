package com.example.esport_clash.team.e2e;

import com.example.esport_clash.IntegrationTest;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.team.application.ports.TeamRepository;
import com.example.esport_clash.team.domain.model.Role;
import com.example.esport_clash.team.domain.model.Team;
import com.example.esport_clash.team.infrastructure.spring.dto.AddPlayerToTeamDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class AddPlayerToTeamE2ETests extends IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    Player player;
    Team team;

    @BeforeEach
    public void setUp() {
        playerRepository.clear();
        teamRepository.clear();

        player = new Player("123", "Player");
        team = new Team("123", "Team1");

        playerRepository.save(player);
        teamRepository.save(team);
    }

    @Test
    void shouldAddPlayerToTeam() throws Exception {
        var dto = new AddPlayerToTeamDTO(player.getId(), team.getId(), "TOP");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/teams/add-player-to-team")
                        .header("Authorization", createJWT())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(MockMvcResultMatchers.status().isOk());

        var expectedTeams = teamRepository.findById(team.getId()).get();

        Assertions.assertTrue(expectedTeams.hasMember(player.getId(), Role.TOP));
    }

    @Test
    void whenPlayerIsAlreadyInTheTeam_ShouldThrow() throws Exception {
        team.addMember(player.getId(), Role.JUNGLE);
        teamRepository.save(team);

        var dto = new AddPlayerToTeamDTO(player.getId(), team.getId(), "TOP");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/teams/add-player-to-team")
                                .header("Authorization", createJWT())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
