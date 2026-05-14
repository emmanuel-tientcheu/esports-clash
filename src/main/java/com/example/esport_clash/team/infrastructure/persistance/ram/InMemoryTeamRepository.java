package com.example.esport_clash.team.infrastructure.persistance.ram;

import com.example.esport_clash.core.infrastructure.persistance.ram.InMemoryBaseRepository;
import com.example.esport_clash.team.application.services.TeamRepository;
import com.example.esport_clash.team.domain.model.Team;

public class InMemoryTeamRepository extends InMemoryBaseRepository<Team> implements TeamRepository {
}
