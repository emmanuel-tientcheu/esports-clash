package com.example.esport_clash.player.infrastructure.persistance.jpa;

import com.example.esport_clash.player.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLPlayerDataAccessor extends JpaRepository<Player, String> {
}
