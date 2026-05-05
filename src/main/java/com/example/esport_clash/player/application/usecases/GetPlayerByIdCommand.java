package com.example.esport_clash.player.application.usecases;

import an.awesome.pipelinr.Command;
import com.example.esport_clash.player.domain.viewModel.PlayerViewResponse;

public class GetPlayerByIdCommand implements Command<PlayerViewResponse> {
    private String id;

    public GetPlayerByIdCommand(String id) { this.id = id; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
