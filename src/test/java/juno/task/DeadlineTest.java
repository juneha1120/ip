package juno.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTest {
    @Test
    public void deadline_nullTaskName_throwsException() {
        try {
            LocalDate exampleBy = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Deadline(null, exampleBy);
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void deadline_nullBy_throwsException() {
        try {
            new Deadline("Deadline 1", (LocalDateTime) null);
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void deadline_invalidBy_throwsException() {
        try {
            LocalDate invalidBy = LocalDate.parse("1/13/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Deadline("Deadline 1", invalidBy);
        } catch (DateTimeParseException ignored) {
        }
    }
}
