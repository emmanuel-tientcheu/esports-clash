package com.example.esport_clash.player.domain.model;

import com.example.esport_clash.core.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    @Id
    private String id;
    @Column
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

    public void rename(String name) { this.name = name; }
}
