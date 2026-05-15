package com.example.esport_clash.schedule;

import com.example.esport_clash.schedule.applications.useCases.CancelMatchCommand;
import com.example.esport_clash.schedule.applications.useCases.CancelMatchCommandHandler;
import com.example.esport_clash.schedule.infrastructure.persistance.ram.InMemoryScheduleRepository;
import com.example.esport_clash.schedule.model.Moment;
import com.example.esport_clash.schedule.model.Schedule;
import com.example.esport_clash.team.domain.model.Role;
import com.example.esport_clash.team.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CancelMatchTest {
    InMemoryScheduleRepository scheduleRepository = new InMemoryScheduleRepository();

    public CancelMatchCommandHandler commandHandler() {
        return new CancelMatchCommandHandler(scheduleRepository);
    }

    @BeforeEach
    void setUp() {
        scheduleRepository.clear();
    }
    public Team createTeam(String id) {
        var team = new Team(id, id+"team");
        team.addMember(id + "-1", Role.TOP);
        team.addMember(id + "-2", Role.JUNGLE);
        team.addMember(id + "-3", Role.MIDDLE);
        team.addMember(id + "-4", Role.BOTTOM);
        team.addMember(id + "-5", Role.SUPPORT);
        return team;
    }

    @Test
    void shouldCancelTest() {
        var schedule = new Schedule("1", LocalDate.now());
        var t1 = createTeam("t1");
        var t2 = createTeam("t2");
        var t3 = createTeam("t3");
        var t4 = createTeam("t4");

        var match = schedule.organize(t1, t2, Moment.MORNING);
        schedule.organize(t3, t4, Moment.AFTERNOON);
        scheduleRepository.save(schedule);

        var command = new CancelMatchCommand(match.getId());
        var useCase = commandHandler();

        useCase.handle(command);

        var updatedSchedule = scheduleRepository.findById(schedule.getId()).get();
        Assertions.assertFalse(
                updatedSchedule.getAt(Moment.MORNING).isPresent()
        );
    }


    @Test
    void whensScheduleDontHaveMatch_shouldDeleteIt() {
        var schedule = new Schedule("1", LocalDate.now());
        var t1 = createTeam("t1");
        var t2 = createTeam("t2");


        var match = schedule.organize(t1, t2, Moment.MORNING);
        scheduleRepository.save(schedule);

        var command = new CancelMatchCommand(match.getId());
        var useCase = commandHandler();

        useCase.handle(command);

        var updatedSchedule = scheduleRepository.findById(schedule.getId());
        Assertions.assertFalse(
                updatedSchedule.isPresent()
        );
    }

}
