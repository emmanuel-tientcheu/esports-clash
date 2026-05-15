package com.example.esport_clash.schedule.model;

import com.example.esport_clash.core.domain.model.BaseEntity;
import com.example.esport_clash.team.domain.model.Team;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Schedule extends BaseEntity<Schedule> {
    private LocalDate day;

    private Map<Moment, Match> matches;


    public Schedule(String id) {
        super(id);
        this.matches = new EnumMap<>(Moment.class);
    }

    @Override
    public Schedule deepClone() {
        return null;
    }

    public Match organize(Team t1, Team t2, Moment moment) {
       if (matches.containsKey(moment)) {
           throw new IllegalStateException("Moment " + moment + " is already taken");
       }

       var anyTeamAlreadyPlay = matches.values().stream().anyMatch(
               match -> match.includeTeam(t1.getId()) || match.includeTeam(t2.getId())
       );

       if (anyTeamAlreadyPlay) {
           throw new IllegalStateException("One of the teams is already playing");
       }

       if (!t1.isCompleted() || !t2.isCompleted()) {
           throw new IllegalStateException("One of the teams is uncompleted");
       }

        var match = new Match(
                UUID.randomUUID().toString(),
                t1.getId(),
                t2.getId()
        );
        this.matches.put(moment, match);

        return match;
    }

    public void cancel(String matchId) {
        var moment = matches.keySet().stream()
                .filter(m -> matches.get(m).getId().equals(matchId))
                .findFirst();

        moment.ifPresent(matches::remove);
    }

    public Optional<Moment> getAt(Moment moment) {
        return matches.containsKey(moment) ? Optional.of(moment) : Optional.empty();
    }
}
