package org.example.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MyLocalDateTimeTest {
    @Test
    void testGetLocalDateTime() {
        LocalDateTime now = MyLocalDateTime.getLocalDateTime();
        assertNotNull(now);
    }

    @Test
    void testParseLocalDateTimeToString() {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 12, 31, 23, 59, 59);
        String formattedDateTime = MyLocalDateTime.parseLocalDateTimeToString(localDateTime);
        assertEquals("31-12-2022 23:59:59", formattedDateTime);
    }

    @Test
    void testGetLocalDateTimeInString() {
        String formattedDateTime = MyLocalDateTime.getLocalDateTimeInString();
        assertNotNull(formattedDateTime);
    }
}
