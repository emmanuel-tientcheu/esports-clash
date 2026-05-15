package com.example.esport_clash.team.application.ports;

import com.example.esport_clash.core.infrastructure.persistance.BaseRepository;
import com.example.esport_clash.team.domain.model.Team;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface TeamRepository extends BaseRepository<Team> {
    public Optional<Team> getTeamByPlayerId(String id);
}
