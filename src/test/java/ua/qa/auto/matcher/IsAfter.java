import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IsAfter extends BaseMatcher<String> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS");

    public IsAfter(LocalDateTime referenceDateTime) {
        this.referenceDateTime = referenceDateTime;
    }

    private final LocalDateTime referenceDateTime;

    @Override
    public boolean matches(Object o) {
        if (o instanceof String timestamp) {
            LocalDateTime dateTimeFromTimestamp = LocalDateTime.parse(timestamp, DATE_TIME_FORMATTER);
            return dateTimeFromTimestamp.isAfter(referenceDateTime);
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("timestamp should be after " + referenceDateTime);
    }
}
