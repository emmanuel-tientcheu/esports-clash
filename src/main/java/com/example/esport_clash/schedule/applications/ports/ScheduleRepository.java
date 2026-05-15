package com.example.esport_clash.schedule.applications.ports;

import com.example.esport_clash.core.infrastructure.persistance.BaseRepository;
import com.example.esport_clash.schedule.model.Schedule;

import java.time.LocalDate;
import java.util.Optional;

public interface ScheduleRepository extends BaseRepository<Schedule> {
    Optional<Schedule> findByDay(LocalDate date);
    Optional<Schedule> findByMatchId(String id);
}
