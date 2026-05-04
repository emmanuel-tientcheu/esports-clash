package com.example.esport_clash.player.infrastructure.persistance.ram;

import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPlayerRepository implements PlayerRepository {
    private Map<String, Player> players = new HashMap<>();

    @Override
    public Optional<Player> findById(String id) {
        return Optional.ofNullable(players.get(id));
    }

    @Override
    public void save(Player palyer) {
        players.put(palyer.getId(), palyer);
    }
}
