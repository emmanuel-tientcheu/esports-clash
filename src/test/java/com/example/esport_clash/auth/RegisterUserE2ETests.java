package com.example.esport_clash.auth;

import com.example.esport_clash.PostgreSQLTestConfiguration;
import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.auth.infrastructure.spring.RegisterUserDTO;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import com.example.esport_clash.player.infrastructure.spring.CreatePlayerDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
@Transactional

public class RegisterUserE2ETests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    public void setUp() {
        repository.clear();
    }

    @Test
    public void shouldRegisterUser() throws Exception {
        var dto = new RegisterUserDTO("emmanuel@gmail.com", "password");

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var IdResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                com.example.esport_clash.player.domain.viewModel.IdResponse.class
        );

        var expectedUser = repository.findById(IdResponse.getId());

        Assertions.assertNotNull(IdResponse);
        Assertions.assertEquals(dto.getEmail(), expectedUser.get().getEmail());
    }

    @Test
    public void whenEmailIsNoTAvailable_shouldThrow() throws Exception {
        var user = new User("123", "emmanuel@gmail.com", "password");
        repository.save(user);

        RegisterUserDTO dto = new RegisterUserDTO(user.getEmail(), "password");

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }
}
