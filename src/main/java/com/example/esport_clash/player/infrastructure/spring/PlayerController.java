package com.example.esport_clash.player.infrastructure.spring;

import com.example.esport_clash.player.application.usecases.CreatePlayerUseCase;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final CreatePlayerUseCase useCase;

    PlayerController(final CreatePlayerUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<IdResponse> createPlayer(@RequestBody CreatePlayerDTO dto) {

        var result = useCase.execute(dto.getName());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
