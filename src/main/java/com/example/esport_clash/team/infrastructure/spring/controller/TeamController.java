package com.example.esport_clash.team.infrastructure.spring.controller;

import an.awesome.pipelinr.Pipeline;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import com.example.esport_clash.team.application.useCases.CreateTeamCommand;
import com.example.esport_clash.team.infrastructure.spring.dto.CreateTeamDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
@Transactional
public class TeamController {
    private final Pipeline pipeline;

    public TeamController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping
    ResponseEntity<IdResponse> createTeam(@RequestBody CreateTeamDTO dto) {
        var result = this.pipeline.send(new CreateTeamCommand(dto.getName()));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
