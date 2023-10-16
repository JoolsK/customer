package ua.qa.auto.matcher;

import org.hamcrest.BaseMatcher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DateMatcher extends BaseMatcher<String> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

    protected LocalDateTime parseLocalDateTime(String timestamp) {
        return LocalDateTime.parse(timestamp, DATE_TIME_FORMATTER);
    }

    @Override
    public abstract boolean matches(Object o);

    @Override
    public abstract void describeTo(org.hamcrest.Description description);
}
