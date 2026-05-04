package com.example.esport_clash.player.application.usecases;

import an.awesome.pipelinr.Command;

public class ChangePlayerNameCommand implements Command<Void> {
    private String id;
    private String name;

    public ChangePlayerNameCommand() {}

    public ChangePlayerNameCommand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() { return name ;}

    public String getId() { return id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
