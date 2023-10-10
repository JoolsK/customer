package ua.qa.auto.matcher;

import java.time.LocalDateTime;
public class DateMatchers {

    public static IsToday isToday() {
        return new IsToday();
    }
}
