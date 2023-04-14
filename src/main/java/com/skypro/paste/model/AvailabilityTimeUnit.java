package com.skypro.paste.model;

import java.time.temporal.ChronoUnit;

public enum AvailabilityTimeUnit {
    MINUTES(ChronoUnit.MINUTES),
    HOURS(ChronoUnit.HOURS),
    DAYS(ChronoUnit.DAYS),
    WEEKS(ChronoUnit.WEEKS),
    MONTHS(ChronoUnit.MONTHS);

    private final ChronoUnit value;

    AvailabilityTimeUnit(ChronoUnit chronoUnit) {
        value = chronoUnit;
    }

    public ChronoUnit toChronoUnit () {
        return value;
    }
}
