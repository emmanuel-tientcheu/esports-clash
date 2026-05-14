package com.example.esport_clash.team.e2e;

import com.example.esport_clash.IntegrationTest;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import com.example.esport_clash.team.application.services.TeamRepository;
import com.example.esport_clash.team.infrastructure.spring.dto.CreateTeamDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

public class CreateTeamE2ETest extends IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    TeamRepository repository;

    @Test
    void shouldCreateTeam() throws Exception {
        var dto = new CreateTeamDTO("first team");

        var result = mockMvc.perform(
                MockMvcRequestBuilders.post("/teams")
                        .header("Authorization", createJWT())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))

        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        IdResponse idResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                IdResponse.class
        );

        var team = repository.findById(idResponse.getId()).get();
        Assertions.assertNotNull(team);
        Assertions.assertEquals(dto.getName(), team.getName());
    }

}
