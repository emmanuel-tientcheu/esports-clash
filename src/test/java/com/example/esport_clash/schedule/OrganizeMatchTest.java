package com.example.esport_clash.schedule;

import com.example.esport_clash.schedule.applications.useCases.OrganizeMatchCommand;
import com.example.esport_clash.schedule.applications.useCases.OrganizeMatchCommandHandler;
import com.example.esport_clash.schedule.infrastructure.persistance.ram.InMemoryScheduleRepository;
import com.example.esport_clash.schedule.model.Moment;
import com.example.esport_clash.schedule.model.Schedule;
import com.example.esport_clash.team.domain.model.Role;
import com.example.esport_clash.team.domain.model.Team;
import com.example.esport_clash.team.infrastructure.persistance.ram.InMemoryTeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDate;

public class OrganizeMatchTest {
    InMemoryScheduleRepository scheduleRepository = new InMemoryScheduleRepository();
    InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

    private OrganizeMatchCommandHandler commandHandler() {
        return new OrganizeMatchCommandHandler(scheduleRepository, teamRepository);
    }

    public Team createTeam(String id) {
        var team = new Team("t1", id+"team");
        team.addMember(id + "-1", Role.TOP);
        team.addMember(id + "-2", Role.JUNGLE);
        team.addMember(id + "-3", Role.MIDDLE);
        team.addMember(id + "-4", Role.BOTTOM);
        team.addMember(id + "-5", Role.SUPPORT);
        return team;
    }

    @BeforeEach
    void setUp() {
        scheduleRepository.clear();
        teamRepository.clear();
    }

    @Test
    void shouldOrganizeTest() {
        var team1 = createTeam("t1");
        var team2 = createTeam("t2");

        this.teamRepository.save(team1);
        this.teamRepository.save(team2);

        var command = new OrganizeMatchCommand(
                LocalDate.parse("2026-01-01"),
                team1.getId(),
                team2.getId(),
                Moment.MORNING
        );

        var useCase = commandHandler();
        var response = useCase.handle(command);

        var schedule = this.scheduleRepository.findByDay(command.getDate());
        Assertions.assertTrue(schedule.isPresent());

        var match = schedule.get().getAt(Moment.MORNING).get();

        Assertions.assertEquals(team1.getId(), match.getFirstId());
        Assertions.assertEquals(response.getId(), match.getId());
    }

    @Test
    void whenScheduleDayAlreadyExist_shouldReuseIt() {
        var team1 = createTeam("t1");
        var team2 = createTeam("t2");

        this.teamRepository.save(team1);
        this.teamRepository.save(team2);

        var date = LocalDate.parse("2026-01-01");
        var schedule = new Schedule("1", date);
        this.scheduleRepository.save(schedule);

        var command = new OrganizeMatchCommand(
                date,
                team1.getId(),
                team2.getId(),
                Moment.MORNING
        );

        var useCase = commandHandler();
        var response = useCase.handle(command);

        var expectedScheduleDay = this.scheduleRepository.findById(schedule.getId()).get();


        var match = expectedScheduleDay.getAt(Moment.MORNING).get();

        Assertions.assertEquals(team1.getId(), match.getFirstId());
        Assertions.assertEquals(response.getId(), match.getId());
    }
}
