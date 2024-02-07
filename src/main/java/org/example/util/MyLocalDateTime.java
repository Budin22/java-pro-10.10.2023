package org.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyLocalDateTime {
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime getLocalDateTimeFromString(String timestamp) {
        return LocalDateTime.parse(timestamp, FORMATTER);
    }
}
