package com.example.esport_clash.team.infrastructure.spring.controller;

import an.awesome.pipelinr.Pipeline;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import com.example.esport_clash.team.application.useCases.*;
import com.example.esport_clash.team.domain.viewModel.TeamViewModel;
import com.example.esport_clash.team.infrastructure.spring.dto.AddPlayerToTeamDTO;
import com.example.esport_clash.team.infrastructure.spring.dto.CreateTeamDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@Transactional
public class TeamController {
    private final Pipeline pipeline;

    public TeamController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @GetMapping("/{id}")
    ResponseEntity<TeamViewModel> getTeamById(@PathVariable String id) {
        var result = this.pipeline.send(new GetTeamByIdCommand(id));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<IdResponse> createTeam(@RequestBody CreateTeamDTO dto) {
        var result = this.pipeline.send(new CreateTeamCommand(dto.getName()));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTeam(@PathVariable("id") String id) {
        this.pipeline.send(new DeleteTeamCommand(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add-player-to-team")
    ResponseEntity<Void> addPlayerToTeam(@RequestBody AddPlayerToTeamDTO dto) {
        this.pipeline.send(new AddPlayerToTeamCommand(dto.getPlayerId(), dto.getTeamId(),  dto.getRole()));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove-player-from-team")
    ResponseEntity<Void> removePlayerFromTeam(@RequestBody AddPlayerToTeamDTO dto) {
        this.pipeline.send(new RemovePlayerFromTeamCommand(dto.getPlayerId(), dto.getTeamId()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
