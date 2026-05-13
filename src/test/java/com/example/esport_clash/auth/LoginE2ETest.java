package com.example.esport_clash.auth;

import com.example.esport_clash.PostgreSQLTestConfiguration;
import com.example.esport_clash.auth.application.ports.UserRepository;
import com.example.esport_clash.auth.application.services.passwordHasher.BcryptPasswordHasher;
import com.example.esport_clash.auth.application.services.passwordHasher.PasswordHasher;
import com.example.esport_clash.auth.domain.model.User;
import com.example.esport_clash.auth.domain.viewModel.LoggedUserViewModel;
import com.example.esport_clash.auth.infrastructure.spring.LoginDTO;
import com.example.esport_clash.player.application.ports.PlayerRepository;
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

public class LoginE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordHasher hasher;

    @BeforeEach
    public void setUp() {
        User user = new User("123", "emmanuel@gmail.com", hasher.hash("password"));
        this.repository.save(user);
    }

    @Test
    public void shouldLoginUser() throws Exception {
        var dto = new LoginDTO("emmanuel@gmail.com", "password");

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        LoggedUserViewModel user = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                LoggedUserViewModel.class
        );

        var expectedUser = repository.findById(user.getId()).get();

        Assertions.assertEquals(dto.getEmail(), expectedUser.getEmail());
    }

    @Test
    public void whenUserDoNotExist_shouldThrowNotFound() throws Exception {
        var dto = new LoginDTO("not-found@gmail.com", "password");

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

    }

    @Test
    public void whenPasswordIsIncorrect_shouldThrowBadRequest() throws Exception {
        var dto = new LoginDTO("emmanuel@gmail.com", "bad-password");

        var result = mockMvc
                .perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

    }
}
