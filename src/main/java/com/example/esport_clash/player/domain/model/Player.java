package com.example.esport_clash.player.domain.model;

public class Player {
    private String id;
    private String name;

    public Player(final String id, final String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }
}
