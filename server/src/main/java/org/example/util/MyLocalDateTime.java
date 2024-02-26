package org.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyLocalDateTime {
    private static final String DATE_FORMATTER= "dd:MM:yyyy HH:mm:ss";
    public static LocalDateTime getLocalDateTime(){
        return LocalDateTime.now();
    }

    public static String parseLocalDateTimeToString(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return localDateTime.format(formatter);
    }

    public static String getLocalDateTimeInString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return LocalDateTime.now().format(formatter);
    }
}
