package com.example.esport_clash.player.infrastructure.spring;

public class RenamePlayerDTO {
    private String id;
    private String name;

    public RenamePlayerDTO() {}

    public RenamePlayerDTO(String id, String name) {
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
