package com.example.esport_clash.schedule.applications.useCases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.player.domain.viewModel.IdResponse;
import com.example.esport_clash.schedule.model.Moment;

import java.time.LocalDate;

public class OrganizeMatchCommand implements Command<IdResponse> {
    private LocalDate date;
    private String firstTeamId;
    private String secondTeamId;
    private Moment moment;

    public OrganizeMatchCommand() {}

    public OrganizeMatchCommand(LocalDate date, String firstTeamId, String secondTeamId, Moment moment) {
        this.date = date;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.moment = moment;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getFirstTeamId() {
        return firstTeamId;
    }

    public String getSecondTeamId() {
        return secondTeamId;
    }

    public Moment getMoment() {
        return moment;
    }
}
