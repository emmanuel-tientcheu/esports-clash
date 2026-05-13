package com.example.esport_clash.player;

import com.example.esport_clash.IntegrationTest;
import com.example.esport_clash.PostgreSQLTestConfiguration;
import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.application.services.jwtservice.JWTService;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;
import com.example.esport_clash.player.domain.viewModel.PlayerViewResponse;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import com.example.esport_clash.player.infrastructure.spring.CreatePlayerDTO;
import com.example.esport_clash.player.infrastructure.spring.RenamePlayerDTO;
import jakarta.transaction.Transactional;
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

public class GetPlayerByIdE2ETests extends IntegrationTest {

    @Autowired
    private MockMvc  mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository repository;



    @Test
    public void shouldGetPlayer() throws Exception {
        Player existingPlayer = new Player("123", "old name");
        repository.save(existingPlayer);

        var result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/players/123")
                                .header("Authorization", createJWT())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

       var playerViewResponse = objectMapper.readValue(
               result.getResponse().getContentAsString(),
               PlayerViewResponse.class
       );

        Assertions.assertEquals(playerViewResponse.getId(), existingPlayer.getId());
        Assertions.assertEquals(playerViewResponse.getName(), existingPlayer.getName());

    }

    @Test
    public void whenDoesNotPlayer_ShouldThrow() throws Exception {

        var dto = new RenamePlayerDTO("garbage", "new name");

        var result = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/players/123")
                                .header("Authorization", createJWT())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());



    }
}
