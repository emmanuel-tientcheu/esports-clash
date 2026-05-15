package com.example.esport_clash.schedule.model;

public enum Moment {
    MORNING,
    AFTERNOON;

    public static Moment fromString(String moment) {
        return switch (moment) {
            case "MORNING" -> Moment.MORNING;
            case "AFTERNOON" -> Moment.AFTERNOON;
            default -> throw new IllegalArgumentException("");
        };
    }
}
