package com.example.esport_clash.player.domain.model;

import com.example.esport_clash.core.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Player extends BaseEntity<Player> {
    @Column
    private String name;

    public Player() {}

    public Player(final String id, final String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public Player deepClone() {
        return new Player(this.id, this.name);
    }

    public void rename(String name) { this.name = name; }
}
