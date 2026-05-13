package com.example.esport_clash;


import com.example.esport_clash.auth.application.services.jwtservice.JWTService;
import com.example.esport_clash.auth.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLTestConfiguration.class)
@Transactional
public class IntegrationTest {
    @Autowired
    protected JWTService jwtService;

    protected String createJWT() {
        var user = new User("123", "emmanuel@gmail.com", "password");
        return "Bearer "+this.jwtService.tokenize(user);
    }
}
