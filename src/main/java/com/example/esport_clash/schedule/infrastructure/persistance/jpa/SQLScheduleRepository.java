package com.example.esport_clash.schedule.infrastructure.persistance.jpa;

import com.example.esport_clash.core.infrastructure.persistance.sql.SQLBaseRepository;
import com.example.esport_clash.schedule.applications.ports.ScheduleRepository;
import com.example.esport_clash.schedule.model.Schedule;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.Optional;

public class SQLScheduleRepository extends SQLBaseRepository<Schedule> implements ScheduleRepository {
    @Override
    public Optional<Schedule> findByMatchId(String id) {
        return Optional.empty();
    }

    public SQLScheduleRepository(EntityManager entity) {
        super(entity);
    }

    @Override
    public Class<Schedule> getEntityMangerClass() {
        return Schedule.class;
    }

    @Override
    public Optional<Schedule> findByDay(LocalDate date) {
        return Optional.empty();
    }
}
