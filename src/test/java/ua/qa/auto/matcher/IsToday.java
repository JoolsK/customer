package ua.qa.auto.matcher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IsToday extends DateMatcher {

    @Override
    public boolean matches(Object o) {
        if (o instanceof String timestamp) {
            LocalDateTime dateTimeFromTimestamp = parseLocalDateTime(timestamp);
            LocalDate dateFromTimestamp = dateTimeFromTimestamp.toLocalDate();
            LocalDate today = LocalDate.now();
            return today.isEqual(dateFromTimestamp);
        }
        return false;
    }

    @Override
    public void describeTo(org.hamcrest.Description description) {
        description.appendText("timestamp should be today");
    }
}