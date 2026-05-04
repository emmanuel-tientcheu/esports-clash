package com.example.esport_clash.player.application.ports;

import com.example.esport_clash.player.domain.model.Player;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PlayerRepository {
    Optional<Player> findById(String id);

    void save(Player palyer);
}
