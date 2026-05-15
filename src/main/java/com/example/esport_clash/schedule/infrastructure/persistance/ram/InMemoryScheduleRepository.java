package com.example.esport_clash.schedule.infrastructure.persistance.ram;

import com.example.esport_clash.core.infrastructure.persistance.ram.InMemoryBaseRepository;
import com.example.esport_clash.schedule.applications.ports.ScheduleRepository;
import com.example.esport_clash.schedule.model.Schedule;

import java.time.LocalDate;
import java.util.Optional;

public class InMemoryScheduleRepository extends InMemoryBaseRepository<Schedule> implements ScheduleRepository {
    @Override
    public Optional<Schedule> findByDay(LocalDate date) {
        return this.entities.values()
                .stream().filter(schedule -> schedule.getDay().equals(date))
                .findFirst();
    }

    @Override
    public Optional<Schedule> findByMatchId(String id) {
        return this.entities.values()
                .stream()
                .filter(schedule -> schedule.getMatches().values().stream().anyMatch(match -> match.getId().equals(id)))
                .findFirst();
    }
}
