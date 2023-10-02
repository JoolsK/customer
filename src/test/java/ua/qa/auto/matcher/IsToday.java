package ua.qa.auto.matcher;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IsToday extends BaseMatcher<String> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

    @Override
    public boolean matches(Object o) {
        if (o instanceof String) {
            String timestamp = (String) o;
            LocalDateTime dateTimeFromTimestamp = LocalDateTime.parse(timestamp, DATE_TIME_FORMATTER);
            LocalDate dateFromTimestamp = dateTimeFromTimestamp.toLocalDate();
            LocalDate today = LocalDate.now();
            return today.isEqual(dateFromTimestamp);
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("timestamp should be today");
    }

    public static IsToday isToday() {
        return new IsToday();
    }
}