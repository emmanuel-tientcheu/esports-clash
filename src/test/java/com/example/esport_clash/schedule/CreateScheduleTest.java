package com.example.esport_clash.schedule;

import com.example.esport_clash.schedule.model.Moment;
import com.example.esport_clash.schedule.model.Schedule;
import com.example.esport_clash.team.domain.model.Role;
import com.example.esport_clash.team.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateScheduleTest {
    public Team createTeam(String id) {
        var team = new Team("t1", id+"team");
        team.addMember(id + "-1", Role.TOP);
        team.addMember(id + "-2", Role.JUNGLE);
        team.addMember(id + "-3", Role.MIDDLE);
        team.addMember(id + "-4", Role.BOTTOM);
        team.addMember(id + "-5", Role.SUPPORT);
        return team;
    }

    public Team createUncompletedTeam(String id) {
        var team = new Team("t1", id+"team");
        team.addMember(id + "-1", Role.TOP);
        team.addMember(id + "-2", Role.JUNGLE);
        team.addMember(id + "-3", Role.MIDDLE);
        team.addMember(id + "-4", Role.BOTTOM);
        return team;
    }

    @Test
    void shouldOrganize() {
        var team1 = createTeam("t1");
        var team2 = createTeam("t2");
        var moment = Moment.MORNING;

        var schedule = new Schedule("1");
        schedule.organize(team1, team2, moment);

        var match = schedule.getAt(moment);
        assertTrue(match.isPresent());
    }

    @Test
    void whenMomentIsAlreadyTaken_shouldThrow() {
        var team1 = createTeam("t1");
        var team2 = createTeam("t2");
        var moment = Moment.MORNING;

        var schedule = new Schedule("1");
        schedule.organize(team1, team2, moment);

       var exception = assertThrows(
               IllegalStateException.class,
               () -> schedule.organize(team1, team2, moment)
       );

       assertEquals("Moment MORNING is already taken", exception.getMessage());
    }

    @Test
    void whenTeamAlreadyPlat_shouldThrow() {
        var team1 = createTeam("t1");
        var team2 = createTeam("t2");
        var team3 = createTeam("t3");

        var moment = Moment.MORNING;

        var schedule = new Schedule("1");
        schedule.organize(team1, team2, moment);

        var exception = assertThrows(
                IllegalStateException.class,
                () -> schedule.organize(team1, team3, Moment.AFTERNOON)
        );

        assertEquals("One of the teams is already playing", exception.getMessage());
    }

    @Test
    void whenTeamIsUncompleted_shouldThrow() {
        var team1 = createUncompletedTeam("t1");
        var team2 = createTeam("t2");

        var moment = Moment.MORNING;

        var schedule = new Schedule("1");

        var exception = assertThrows(
                IllegalStateException.class,
                () -> schedule.organize(team1, team2, moment)
        );

        assertEquals("One of the teams is uncompleted", exception.getMessage());
    }

    @Test
    void shouldCancel() {
        var team1 = createTeam("t1");
        var team2 = createTeam("t2");
        var moment = Moment.MORNING;

        var schedule = new Schedule("1");
        var organizeMatch =  schedule.organize(team1, team2, moment);

        schedule.cancel(organizeMatch.getId());
        var match = schedule.getAt(moment);
        Assertions.assertFalse(match.isPresent());
    }
}
