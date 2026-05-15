package com.example.esport_clash.schedule.applications.useCases;

import an.awesome.pipelinr.Command;

public class CancelMatchCommand implements Command<Void> {
    private String matchId;

    public CancelMatchCommand() {}

    public CancelMatchCommand(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }
}
