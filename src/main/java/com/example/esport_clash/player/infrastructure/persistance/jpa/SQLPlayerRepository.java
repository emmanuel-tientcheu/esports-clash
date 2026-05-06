package com.example.esport_clash.player.infrastructure.persistance.jpa;

import com.example.esport_clash.core.infrastructure.persistance.sql.SQLBaseRepository;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.OptionalLong;

public class SQLPlayerRepository extends SQLBaseRepository<Player>implements PlayerRepository {

    public SQLPlayerRepository(final EntityManager dataAccessor) {
        super(dataAccessor);
    }

//    @Override
//    public Optional<Player> findById(String id) {
//        return dataAccessor.findById(id)
//                .map(sqlPlayer -> new Player(
//                        sqlPlayer.getId(),
//                        sqlPlayer.getName()
//                ));
//    }



    @Override
    public Class<Player> getEntityMangerClass() {
        return Player.class;
    }
}
