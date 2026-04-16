package com.example.esport_clash.player.infrastructure.persistance.ram;

import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPlayerRepository implements PlayerRepository {
    private Map<String, Player> players = new HashMap<>();

    @Override
    public Player findById(String id) {
        return players.get(id);
    }

    @Override
    public void save(Player palyer) {
        players.put(palyer.getId(), palyer);
    }
}
