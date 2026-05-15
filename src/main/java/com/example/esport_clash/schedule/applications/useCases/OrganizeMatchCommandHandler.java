package com.example.esport_clash.schedule.applications.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import com.example.esport_clash.schedule.applications.ports.ScheduleRepository;
import com.example.esport_clash.schedule.model.Schedule;
import com.example.esport_clash.team.application.ports.TeamRepository;

import java.time.LocalDate;
import java.util.UUID;

public class OrganizeMatchCommandHandler implements Command.Handler<OrganizeMatchCommand, IdResponse> {
    private final ScheduleRepository repository;
    private final TeamRepository teamRepository;

    public OrganizeMatchCommandHandler(ScheduleRepository repository, TeamRepository teamRepository) {
        this.repository = repository;
        this.teamRepository = teamRepository;
    }

    @Override
    public IdResponse handle(OrganizeMatchCommand command) {

        var t1 = teamRepository.findById(command.getFirstTeamId()).orElseThrow(
                () -> new NotFoundException("Team", command.getFirstTeamId())
        );

        var t2 = teamRepository.findById(command.getFirstTeamId()).orElseThrow(
                () -> new NotFoundException("Team", command.getSecondTeamId())
        );

        var schedule = this.repository.findByDay(command.getDate()).orElse(
                new Schedule(
                        UUID.randomUUID().toString(),
                        command.getDate()
                )
        );


        var match = schedule.organize(t1, t2, command.getMoment());
        this.repository.save(schedule);

        return new IdResponse(match.getId());
    }
}
