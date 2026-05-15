package com.example.esport_clash.schedule.model;

import com.example.esport_clash.core.domain.model.BaseEntity;
import com.example.esport_clash.team.domain.model.Team;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Schedule extends BaseEntity<Schedule> {
    private LocalDate day;

    private Map<Moment, Match> matches;


    public Schedule() {
    }

    public Schedule(String id, LocalDate day) {
        super(id);
        this.day = day;
        this.matches = new EnumMap<>(Moment.class);
    }

    private Schedule(String id, LocalDate day, Map<Moment, Match> matches) {
        super(id);
        this.day = day;
        this.matches = new EnumMap<>(Moment.class);
        this.matches = matches;
    }

    public Map<Moment, Match> getMatches() {
        return matches;
    }

    @Override
    public Schedule deepClone() {
        return new Schedule(
                this.id,
                this.day,
                matches.keySet().stream()
                        .collect(
                                () -> new EnumMap<>(Moment.class),
                                (map, moment) -> map.put(moment, matches.get(moment).deepClone()),
                                Map::putAll
                        )
        );
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

    public Optional<Match> getAt(Moment moment) {
        return matches.containsKey(moment) ? Optional.of(matches.get(moment)) : Optional.empty();
    }

    public LocalDate getDay() {
        return day;
    }

    public boolean hasMatches() {
        return !this.matches.isEmpty();
    }
}
