package com.example.esport_clash.player.infrastructure.persistance.jpa;

import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;

public class SQLPlayerRepository implements PlayerRepository {
    private final SQLPlayerDataAccessor dataAccessor;

    public SQLPlayerRepository(final SQLPlayerDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    @Override
    public Player findById(String id) {
        var sqlPlayerQuery = this.dataAccessor.findById(id);

        if(sqlPlayerQuery.isEmpty()) { return null; }
        var sqlPlayer = sqlPlayerQuery.get();
        return new Player(
                sqlPlayer.getId(),
                sqlPlayer.getName()
        );
    }

    @Override
    public void save(Player player) {
        var sqlPlayer = new SQLPlayer(player.getId(), player.getName());
        this.dataAccessor.save(sqlPlayer);
    }
}
