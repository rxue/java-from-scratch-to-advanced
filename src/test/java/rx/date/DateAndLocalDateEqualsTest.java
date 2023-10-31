package rx.date;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateAndLocalDateEqualsTest {
    @Test
    public void test() {
        Date legacyDate = getLegacyDate("10.10.2021");
        Instant instant = getInstant(LocalDate.of(2021, 10, 10));
        assertEquals(legacyDate.toInstant(), instant);
    }

    private Instant getInstant(LocalDate localDate) {
        LocalDateTime midnightDate = localDate.atTime(LocalTime.MIDNIGHT);
        Instant instant = midnightDate.atZone(ZoneId.systemDefault()).toInstant();
        return instant;
    }

    Date getLegacyDate(String dateValue) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return df.parse(dateValue);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
