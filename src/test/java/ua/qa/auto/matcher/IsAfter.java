package ua.qa.auto.matcher;

import java.time.LocalDateTime;

public class IsAfter extends DateMatcher {

    private final LocalDateTime referenceDateTime;

    public IsAfter(LocalDateTime referenceDateTime) {
        this.referenceDateTime = referenceDateTime;
    }

    @Override
    public boolean matches(Object o) {
        if (o instanceof String timestamp) {
            LocalDateTime dateTimeFromTimestamp = parseLocalDateTime(timestamp);
            return dateTimeFromTimestamp.isAfter(referenceDateTime);
        }
        return false;
    }

    @Override
    public void describeTo(org.hamcrest.Description description) {
        description.appendText("timestamp should be after " + referenceDateTime);
    }
}
