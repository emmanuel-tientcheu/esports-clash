package com.example.esport_clash.schedule.model;

import com.example.esport_clash.core.domain.model.BaseEntity;

public class Match extends BaseEntity<Match> {

    private String firstId;

    private String secondId;

    public Match() {}

    public Match(String id, String firstId, String secondId) {
        super(id);
        this.firstId = firstId;
        this.secondId = secondId;
    }

    @Override
    public Match deepClone() {
        return null;
    }

    public String getFirstId() {
        return firstId;
    }

    public String getSecondId() {
        return secondId;
    }

    public boolean includeTeam(String id) {
        return firstId.equals(id) || secondId.equals(id);
    }
}
