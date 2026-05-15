package com.example.esport_clash.team.e2e;

import com.example.esport_clash.IntegrationTest;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.team.application.ports.TeamRepository;
import com.example.esport_clash.team.domain.model.Role;
import com.example.esport_clash.team.domain.model.Team;
import com.example.esport_clash.team.domain.viewModel.TeamViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class GetTeamByIdE2ETests extends IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    Player player;
    Team team;

    Role role = Role.TOP;

    @BeforeEach
    public void setUp() {
        teamRepository.clear();
        teamRepository.clear();

        player = new Player("123", "Player");
        team = new Team("123", "Team1");
        team.addMember(player.getId(), role);

        playerRepository.save(player);
        teamRepository.save(team);

        clearDatabase();
    }

    @Test
    void shouldGetTeamById() throws Exception {

        var result = mockMvc.perform(
                MockMvcRequestBuilders.get("/teams/"+ team.getId())
                        .header("Authorization", createJWT())

        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var response = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                TeamViewModel.class
        );

        Assertions.assertNotNull(response);

        Assertions.assertEquals(team.getId(), response.getId());
        Assertions.assertEquals(team.getName(), response.getName());

        var firstPlayer = response.getMembers().stream().findFirst().get();

        Assertions.assertEquals(player.getId(), firstPlayer.getPlayerId());
        Assertions.assertEquals(player.getName(), firstPlayer.getPlayerName());
        Assertions.assertEquals(role.toString(), firstPlayer.getRole());


    }
}
