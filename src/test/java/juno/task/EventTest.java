package juno.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTest {
    @Test
    public void event_nullTaskName_throwsException() {
        try {
            LocalDate exampleFrom = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate exampleTo = LocalDate.parse("2/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Event(null, exampleFrom, exampleTo);
        } catch (NullPointerException ignored){
        }
    }

    @Test
    public void event_nullFrom_throwsException() {
        try {
            LocalDate exampleTo = LocalDate.parse("2/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Event("Deadline 1", (LocalDateTime) null, exampleTo);
        } catch (NullPointerException ignored){
        }
    }

    @Test
    public void event_invalidFrom_throwsException() {
        try {
            LocalDate invalidFrom = LocalDate.parse("1/13/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate exampleTo = LocalDate.parse("2/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Event("Deadline 1", invalidFrom, exampleTo);
        } catch (DateTimeParseException ignored){
        }
    }

    @Test
    public void event_nullTo_throwsException() {
        try {
            LocalDate exampleFrom = LocalDate.parse("2/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Event("Deadline 1", exampleFrom, (LocalDateTime) null);
        } catch (NullPointerException ignored){
        }
    }

    @Test
    public void event_invalidTo_throwsException() {
        try {
            LocalDate exampleFrom = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate invalidTo = LocalDate.parse("1/13/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Event("Deadline 1", exampleFrom, invalidTo);
        } catch (DateTimeParseException ignored){
        }
    }
}
