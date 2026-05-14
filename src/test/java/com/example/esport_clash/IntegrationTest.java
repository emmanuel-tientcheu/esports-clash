package com.example.esport_clash;


import com.example.esport_clash.auth.application.ports.UserRepository;
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

    @Autowired
    protected UserRepository userRepository;

    protected String createJWT() {
        var user = this.userRepository.findByEmail("emmanuel@gmail.com").orElse(null);
        if (user == null) {
             user = new User("123", "emmanuel@gmail.com", "password");
             userRepository.save(user);
        }
        return "Bearer "+this.jwtService.tokenize(user);
    }
}
