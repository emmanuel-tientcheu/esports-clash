package com.example.esport_clash.player.application.ports;

import com.example.esport_clash.player.domain.model.Player;

public interface PlayerRepository {
    Player findById(String id);

    void save(Player palyer);
}
