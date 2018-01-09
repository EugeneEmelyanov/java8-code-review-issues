package com.issues.dates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Java8 introduced new {@link LocalDate}, {@link LocalDateTime} and {@link java.time.LocalTime} classes as well
 * as bunch of items to simplify date and time manipulation, including {@link java.time.ZonedDateTime}, {@link java.time.Period},
 * {@link java.time.Duration} and others.
 *
 * Here is a nice intro: http://www.baeldung.com/java-8-date-time-intro
 *
 * Please, see additional guide of how to migrate from Joda time to Java 8 Date API:
 * http://blog.joda.org/2014/11/converting-from-joda-time-to-javatime.html
 */
public class DateExamples {

    /**
     * Recommended usage of {@link LocalDate#plusDays(long)} and {@link LocalDate#minusDays(long)} methods.
     * @return
     */
    public LocalDate tomorrow() {
        return LocalDate.now().plusDays(1);
    }

    public LocalDate yesterday() {
        return LocalDate.now().minusDays(1);
    }

    /**
     * Convert {@code LocalDate} to {@code LocalDateTime} to be start of the day.
     * @return
     */
    public LocalDateTime startOfTheDay() {
        return tomorrow().atStartOfDay();
    }

    /**
     * {@code LocalDate} to old {@code Date} conversion.
     * @return
     */
    public Date toDate() {
        LocalDate localDate = LocalDate.now();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Old {@code Date} to {@code LocalDate} conversion.
     * @return
     */
    public LocalDate toLocalDate() {
        Date date = Calendar.getInstance().getTime();
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
