package com.example.esport_clash.player;

import com.example.esport_clash.PostgreSQLTestConfiguration;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import com.example.esport_clash.player.infrastructure.spring.CreatePlayerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLTestConfiguration.class)
public class CreatePlayerE2ETests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository repository;

    @Test
    public void shouldCreatePlayer() throws Exception {
        var dto = new CreatePlayerDTO("name");

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var IdResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                com.example.esport_clash.player.domain.viewModel.IdResponse.class
        );

        var expectedPlayer = repository.findById(IdResponse.getId());

        Assertions.assertNotNull(IdResponse);
        Assertions.assertEquals(dto.getName(), expectedPlayer.getName());
    }
}
