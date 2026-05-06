package com.example.esport_clash.player.infrastructure.persistance.ram;

import com.example.esport_clash.core.infrastructure.persistance.ram.InMemoryBaseRepository;
import com.example.esport_clash.player.application.ports.PlayerRepository;
import com.example.esport_clash.player.domain.model.Player;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPlayerRepository extends InMemoryBaseRepository<Player> implements PlayerRepository {

}
