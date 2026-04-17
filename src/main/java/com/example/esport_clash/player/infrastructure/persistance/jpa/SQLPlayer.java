package com.example.esport_clash.player.infrastructure.persistance.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class SQLPlayer {
    @Id
    public String id;

    @Column
    public String name;

    public SQLPlayer() {}

    public SQLPlayer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId( String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
