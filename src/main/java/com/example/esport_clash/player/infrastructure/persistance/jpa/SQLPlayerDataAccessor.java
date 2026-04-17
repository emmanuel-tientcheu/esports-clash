package com.example.esport_clash.player.infrastructure.persistance.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLPlayerDataAccessor extends JpaRepository<SQLPlayer, String> {
}
