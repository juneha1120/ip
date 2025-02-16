package chillguy.task;

import static chillguy.commands.Command.EXAMPLE_PREFIX;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chillguy.enums.TaskType;

/**
 * Represents a {@code Event} task that has a specified start date/time and end date/time.
 * This class extends the {@link Task} class and encapsulates details about a task
 * with the specified start date/time and end date/time.
 */
public class Event extends Task {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + " : adds task from and to specified date/time to task list.\n"
            + EXAMPLE_PREFIX + COMMAND_WORD
            + " study CS2103T /from 01/01/1000 0100 /to 02/01/1000 0200";
    protected TaskType type = TaskType.EVENT;
    protected LocalDateTime from;
    protected LocalDate fromDate;
    protected LocalDateTime to;
    protected LocalDate toDate;

    /**
     * Constructs an {@code Event} with the specified task name, completion status, start date/time, and end date/time.
     *
     * @param taskName The name of the event.
     * @param isDone The completion status of the event (true if completed, false otherwise).
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String taskName, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(taskName, isDone);
        this.from = from;
        this.fromDate = from.toLocalDate();
        this.to = to;
        this.toDate = to.toLocalDate();
    }

    /**
     * Constructs an {@code Event} with the specified task name, completion status, start date/time, and end date.
     *
     * @param taskName The name of the event.
     * @param isDone The completion status of the event (true if completed, false otherwise).
     * @param from The start date and time of the event.
     * @param toDate The end date of the event.
     */
    public Event(String taskName, boolean isDone, LocalDateTime from, LocalDate toDate) {
        super(taskName, isDone);
        this.from = from;
        this.fromDate = from.toLocalDate();
        this.to = null;
        this.toDate = toDate;
    }

    /**
     * Constructs an {@code Event} with the specified task name, completion status, start date, and end date/time.
     *
     * @param taskName The name of the event.
     * @param isDone The completion status of the event (true if completed, false otherwise).
     * @param fromDate The start date of the event.
     * @param to The end date and time of the event.
     */
    public Event(String taskName, boolean isDone, LocalDate fromDate, LocalDateTime to) {
        super(taskName, isDone);
        this.from = null;
        this.fromDate = fromDate;
        this.to = to;
        this.toDate = to.toLocalDate();
    }

    /**
     * Constructs an {@code Event} with the specified task name, completion status, start date, and end date.
     *
     * @param taskName The name of the event.
     * @param isDone The completion status of the event (true if completed, false otherwise).
     * @param fromDate The start date of the event.
     * @param toDate The end date of the event.
     */
    public Event(String taskName, boolean isDone, LocalDate fromDate, LocalDate toDate) {
        super(taskName, isDone);
        this.from = null;
        this.fromDate = fromDate;
        this.to = null;
        this.toDate = toDate;
    }

    /**
     * Constructs an {@code Event} with the specified task name, start date/time, and end date/time. The event is marked
     * as not done by default.
     *
     * @param taskName The name of the event.
     * @param from The start date and time of the event.
     * @param to The end date and time of the event.
     */
    public Event(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName, false);
        this.from = from;
        this.fromDate = from.toLocalDate();
        this.to = to;
        this.toDate = to.toLocalDate();
    }

    /**
     * Constructs an {@code Event} with the specified task name, start date/time, and end date. The event is marked as
     * not done by default.
     *
     * @param taskName The name of the event.
     * @param from The start date and time of the event.
     * @param toDate The end date of the event.
     */
    public Event(String taskName, LocalDateTime from, LocalDate toDate) {
        super(taskName, false);
        this.from = from;
        this.fromDate = from.toLocalDate();
        this.to = null;
        this.toDate = toDate;
    }

    /**
     * Constructs an {@code Event} with the specified task name, start date, and end date/time. The event is marked as
     * not done by default.
     *
     * @param taskName The name of the event.
     * @param fromDate The start date of the event.
     * @param to The end date and time of the event.
     */
    public Event(String taskName, LocalDate fromDate, LocalDateTime to) {
        super(taskName, false);
        this.from = null;
        this.fromDate = fromDate;
        this.to = to;
        this.toDate = to.toLocalDate();
    }

    /**
     * Constructs an {@code Event} with the specified task name, start date, and end date. The event is marked as not
     * done by default.
     *
     * @param taskName The name of the event.
     * @param fromDate The start date of the event.
     * @param toDate The end date of the event.
     */
    public Event(String taskName, LocalDate fromDate, LocalDate toDate) {
        super(taskName, false);
        this.from = null;
        this.fromDate = fromDate;
        this.to = null;
        this.toDate = toDate;
    }

    /**
     * Returns the {@code TaskType} of the task.
     *
     * @return The {@code TaskType}.
     */
    @Override
    public TaskType getType() {
        return this.type;
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date of the event.
     */
    @Override
    public LocalDate getFromDate() {
        return fromDate;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date of the event.
     */
    @Override
    public LocalDate getToDate() {
        return toDate;
    }

    /**
     * Converts the {@code Event} to a file-friendly format for saving. The format includes the event type ("E"),
     * completion status, task name, start date/time, and end date/time.
     *
     * @return A string representing the {@code Event} task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + this.getTaskName() + " | "
            + (this.from == null ? this.fromDate : this.from) + " | "
            + (this.to == null ? this.toDate : this.to);
    }

    /**
     * Returns a string representation of the {@code Event} task, including its status and start and end date/time.
     *
     * @return A string representing the {@code Event} task.
     */
    @Override
    public String toString() {
        String formattedFrom;
        String formattedTo;

        if (this.from == null) {
            formattedFrom = this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            formattedFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
            formattedFrom = formattedFrom.substring(0, formattedFrom.length() - 2)
                    + formattedFrom.substring(formattedFrom.length() - 2).toLowerCase();
        }

        if (this.to == null) {
            formattedTo = this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            formattedTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
            formattedTo = formattedTo.substring(0, formattedTo.length() - 2)
                    + formattedTo.substring(formattedTo.length() - 2).toLowerCase();
        }

        return "[E]" + super.toString() + " (from " + formattedFrom + " to " + formattedTo + ")";
    }
}
