package ua.qa.auto.matcher;

import java.time.LocalDateTime;

public class DateMatchers {

    private DateMatchers() {
    }

    public static IsToday isToday() {
        return new IsToday();
    }

    public static IsAfter isAfter(LocalDateTime referenceDateTime) {
        return new IsAfter(referenceDateTime);
    }
}
