package chillguy.task;

import static chillguy.commands.Command.EXAMPLE_PREFIX;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chillguy.enums.TaskType;

/**
 * Represents a {@code Deadline} task that has a specified due date/time.
 * This class extends the {@link Task} class and encapsulates details about a task
 * that must be completed by a certain date or time.
 */
public class Deadline extends Task {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD
            + " : adds task due specified date/time to task list.\n"
            + EXAMPLE_PREFIX + COMMAND_WORD + " study CS2103T /by 01/01/1000 0100";
    protected TaskType type = TaskType.DEADLINE;
    protected LocalDateTime by;
    protected LocalDate byDate;

    /**
     * Constructs a {@code Deadline} task with the specified task name, completion status, and due
     * {@code LocalDateTime}.
     *
     * @param taskName The name of the task.
     * @param isDone The completion status of the task (true if completed, false otherwise).
     * @param by The {@code LocalDateTime} by which the task is due.
     */
    public Deadline(String taskName, boolean isDone, LocalDateTime by) {
        super(taskName, isDone);
        this.by = by;
        this.byDate = by.toLocalDate();
    }

    /**
     * Constructs a {@code Deadline} task with the specified task name, completion status, and due {@code LocalDate}.
     *
     * @param taskName The name of the task.
     * @param isDone The completion status of the task (true if completed, false otherwise).
     * @param byDate The {@code LocalDate} by which the task is due.
     */
    public Deadline(String taskName, boolean isDone, LocalDate byDate) {
        super(taskName, isDone);
        this.by = null;
        this.byDate = byDate;
    }

    /**
     * Constructs a {@code Deadline} task with the specified task name and due {@code LocalDateTime}.
     * The task is considered not done initially.
     *
     * @param taskName The name of the task.
     * @param by The {@code LocalDateTime} by which the task is due.
     */
    public Deadline(String taskName, LocalDateTime by) {
        super(taskName, false);
        this.by = by;
        this.byDate = by.toLocalDate();
    }

    /**
     * Constructs a {@code Deadline} task with the specified task name and due {@code LocalDate}.
     * The task is considered not done initially.
     *
     * @param taskName The name of the task.
     * @param byDate The {@code LocalDate} by which the task is due.
     */
    public Deadline(String taskName, LocalDate byDate) {
        super(taskName, false);
        this.by = null;
        this.byDate = byDate;
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
     * Returns the due {@code LocalDate} of the task.
     *
     * @return The {@code LocalDate} by which the task is due.
     */
    @Override
    public LocalDate getByDate() {
        return this.byDate;
    }

    /**
     * Converts the {@code Deadline} to a file-friendly format for saving. The format includes the event type ("D"),
     * completion status, task name, and date/time.
     *
     * @return A string representing the {@code Deadline} task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + this.getTaskName() + " | "
            + (this.by == null ? this.byDate : this.by);
    }

    /**
     * Returns a string representation of the {@code Deadline} task, including its status and due date/time.
     *
     * @return A string representing the {@code Deadline} task.
     */
    @Override
    public String toString() {
        String formatted;

        if (this.by == null) {
            formatted = this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            formatted = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"));
            formatted = formatted.substring(0, formatted.length() - 2)
                    + formatted.substring(formatted.length() - 2).toLowerCase();
        }

        return "[D]" + super.toString() + " (by " + formatted + ")";
    }
}
