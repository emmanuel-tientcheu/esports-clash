package com.example.esport_clash.player.infrastructure.persistance.jpa;

import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;

import java.util.Optional;
import java.util.OptionalLong;

public class SQLPlayerRepository implements PlayerRepository {
    private final SQLPlayerDataAccessor dataAccessor;

    public SQLPlayerRepository(final SQLPlayerDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    @Override
    public Optional<Player> findById(String id) {
        return dataAccessor.findById(id)
                .map(sqlPlayer -> new Player(
                        sqlPlayer.getId(),
                        sqlPlayer.getName()
                ));
    }

    @Override
    public void save(Player player) {
        var sqlPlayer = new SQLPlayer(player.getId(), player.getName());
        this.dataAccessor.save(sqlPlayer);
    }
}
