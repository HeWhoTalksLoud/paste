package com.skypro.paste.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class FormatInstant {
    private static final String PATTERN_FORMAT = "dd.MM.yyyy hh:mm:ss z";

    public static String format(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
