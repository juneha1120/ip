package chillguy.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void event_nullTaskName_throwsException() {
        LocalDate exampleFrom = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
        LocalDate exampleTo = LocalDate.parse("2/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
        assertThrows(AssertionError.class, () -> new Event(null, exampleFrom, exampleTo));
    }

    @Test
    public void event_nullFrom_throwsException() {
        try {
            LocalDate exampleTo = LocalDate.parse("2/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Event("Deadline 1", (LocalDateTime) null, exampleTo);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void event_invalidFrom_throwsException() {
        try {
            LocalDate invalidFrom = LocalDate.parse("1/13/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate exampleTo = LocalDate.parse("2/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Event("Deadline 1", invalidFrom, exampleTo);
        } catch (DateTimeParseException ignored) {
            // Ignored
        }
    }

    @Test
    public void event_nullTo_throwsException() {
        try {
            LocalDate exampleFrom = LocalDate.parse("2/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Event("Deadline 1", exampleFrom, (LocalDateTime) null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void event_invalidTo_throwsException() {
        try {
            LocalDate exampleFrom = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate invalidTo = LocalDate.parse("1/13/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Event("Deadline 1", exampleFrom, invalidTo);
        } catch (DateTimeParseException ignored) {
            // Ignored
        }
    }
}
