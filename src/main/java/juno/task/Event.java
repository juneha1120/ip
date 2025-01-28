package juno.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static juno.commands.Command.EXAMPLE_PREFIX;

public class Event extends Task {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : adds task from and to specified date/time to task list.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " study CS2103T /from 01/01/1000 0100" +
            "\n                                             /to 02/01/1000 0200";

    protected LocalDateTime from;
    protected LocalDate fromDate;
    protected LocalDateTime to;
    protected LocalDate toDate;

    public Event(String taskName, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(taskName, isDone);
        this.from = from;
        this.fromDate = from.toLocalDate();
        this.to = to;
        this.toDate = to.toLocalDate();
    }

    public Event(String taskName, boolean isDone, LocalDate fromDate, LocalDate toDate) {
        super(taskName, isDone);
        this.from = null;
        this.fromDate = fromDate;
        this.to = null;
        this.toDate = toDate;
    }

    public Event(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName, false);
        this.from = from;
        this.fromDate = from.toLocalDate();
        this.to = to;
        this.toDate = to.toLocalDate();
    }

    public Event(String taskName, LocalDateTime from, LocalDate toDate) {
        super(taskName, false);
        this.from = from;
        this.fromDate = from.toLocalDate();
        this.to = null;
        this.toDate = toDate;
    }

    public Event(String taskName, LocalDate fromDate, LocalDateTime to) {
        super(taskName, false);
        this.from = null;
        this.fromDate = fromDate;
        this.to = to;
        this.toDate = to.toLocalDate();
    }

    public Event(String taskName, LocalDate fromDate, LocalDate toDate) {
        super(taskName, false);
        this.from = null;
        this.fromDate = fromDate;
        this.to = null;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + this.getDescription() + " | " +
                (this.from == null ? this.fromDate : this.from) + " | " +
                (this.to == null ? this.toDate : this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " +
                (this.from == null ? this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) :
                        this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mma"))) +
                " to " + (this.to == null ? this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) :
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mma"))) + ")";
    }
}