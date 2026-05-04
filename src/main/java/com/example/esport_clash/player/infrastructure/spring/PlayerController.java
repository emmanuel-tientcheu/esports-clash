package com.example.esport_clash.player.infrastructure.spring;

import an.awesome.pipelinr.Pipeline;
import com.example.esport_clash.player.application.usecases.ChangePlayerNameCommand;
import com.example.esport_clash.player.application.usecases.CreatePlayerCommand;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final Pipeline pipeline;

    PlayerController(final Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping
    public ResponseEntity<IdResponse> createPlayer(@RequestBody CreatePlayerDTO dto) {
        var command = new CreatePlayerCommand(dto.getName());
        var result = this.pipeline.send(command);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/rename")
    public ResponseEntity<Void> changePlayerName(@RequestBody RenamePlayerDTO dto, @PathVariable String id){
        this.pipeline.send(new ChangePlayerNameCommand(id, dto.getName()));
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
