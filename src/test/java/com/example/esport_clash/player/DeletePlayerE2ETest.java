package com.example.esport_clash.player;

import com.example.esport_clash.PostgreSQLTestConfiguration;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import com.example.esport_clash.player.infrastructure.spring.CreatePlayerDTO;
import com.example.esport_clash.player.infrastructure.spring.RenamePlayerDTO;
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
public class DeletePlayerE2ETest {

    @Autowired
    private MockMvc  mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository repository;

    @Test
    public void shouldRenamePlayer() throws Exception {
        Player existingPlayer = new Player("123", "old name");
        repository.save(existingPlayer);


        var result = mockMvc.perform(
                        MockMvcRequestBuilders.delete("/players/"+ existingPlayer.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                               )
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        var playerQuery = repository.findById(existingPlayer.getId());

        Assertions.assertTrue(playerQuery.isEmpty());

    }

    @Test
    public void whenDoesNotPlayer_ShouldThrow() throws Exception {

        var result = mockMvc.perform(
                        MockMvcRequestBuilders.delete("/players/123")
                                .contentType(MediaType.APPLICATION_JSON)
                                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());



    }
}
