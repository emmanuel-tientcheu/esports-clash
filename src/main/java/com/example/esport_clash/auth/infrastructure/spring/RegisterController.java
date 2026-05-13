package com.example.esport_clash.auth.infrastructure.spring;

import an.awesome.pipelinr.Pipeline;
import com.example.esport_clash.auth.application.useCases.LoginCommand;
import com.example.esport_clash.auth.application.useCases.RegisterCommand;
import com.example.esport_clash.auth.domain.viewModel.LoggedUserViewModel;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Transactional
public class RegisterController {
    private final Pipeline pipeline;

    public RegisterController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping("/user")
    public ResponseEntity<IdResponse> register(@RequestBody RegisterUserDTO dto) {
        var result = this.pipeline.send(new RegisterCommand(dto.getEmail(), dto.getPassword()));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoggedUserViewModel> login(@RequestBody LoginDTO dto) {
        var result = this.pipeline.send(new LoginCommand(dto.getEmail(), dto.getPassword()));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
