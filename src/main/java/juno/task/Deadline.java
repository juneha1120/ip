package juno.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static juno.commands.Command.EXAMPLE_PREFIX;

public class Deadline extends Task {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : adds task due specified date/time to task list.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " study CS2103T /by 01/01/1000 0100";

    protected LocalDateTime by;
    protected LocalDate byDate;

    public Deadline(String taskName, boolean isDone, LocalDateTime by) {
        super(taskName, isDone);
        this.by = by;
        this.byDate = by.toLocalDate();
    }

    public Deadline(String taskName, boolean isDone, LocalDate byDate) {
        super(taskName, isDone);
        this.by = null;
        this.byDate = byDate;
    }

    public Deadline(String taskName, LocalDateTime by) {
        super(taskName, false);
        this.by = by;
        this.byDate = by.toLocalDate();
    }

    public Deadline(String taskName, LocalDate byDate) {
        super(taskName, false);
        this.by = null;
        this.byDate = byDate;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + this.getTaskName() + " | " +
                (this.by == null ? this.byDate : this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " +
                (this.by == null ? this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) :
                        this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy H:mma"))) + ")";
    }

}