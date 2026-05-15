package com.example.esport_clash.schedule.applications.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.core.domain.exceptions.NotFoundException;
import com.example.esport_clash.schedule.applications.ports.ScheduleRepository;

public class CancelMatchCommandHandler implements Command.Handler<CancelMatchCommand, Void> {
    private final ScheduleRepository repository;

    public CancelMatchCommandHandler(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Void handle(CancelMatchCommand command) {
        var schedule = this.repository.findByMatchId(command.getMatchId()).orElseThrow(
                ()-> new NotFoundException("Match not found in this schedule", "")
        );

        schedule.cancel(command.getMatchId());

        if(schedule.hasMatches()) {
            this.repository.save(schedule);
        } else {
            this.repository.delete(schedule);
        }

        return null;
    }
}
