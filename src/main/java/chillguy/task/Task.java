package chillguy.task;

import java.time.LocalDate;

import chillguy.enums.TaskType;

/**
 * Represents a generic task with a name and completion status.
 * This class provides basic task functionality that can be extended by more specific task types
 * like {@link Deadline}, {@link Event}, and {@link Todo}.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;
    protected TaskType type;
    protected LocalDate byDate;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructs a {@code Task} with the specified name and sets its completion status to {@code false}.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        assert taskName != null && !taskName.isEmpty() : "Task name cannot be null or empty";
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task} with the specified name and completion status.
     *
     * @param taskName The name of the task.
     * @param isDone The completion status of the task (true if completed, false otherwise).
     */
    public Task(String taskName, boolean isDone) {
        assert taskName != null && !taskName.isEmpty() : "Task name cannot be null or empty";
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns whether the task is marked as completed.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the {@code TaskType} of the task.
     *
     * @return The {@code TaskType}.
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the due {@code LocalDate} of the task.
     *
     * @return The {@code LocalDate} by which the task is due.
     */
    public LocalDate getByDate() {
        return this.byDate;
    }

    /**
     * Returns the start {@code LocalDate} of the task.
     *
     * @return The start {@code LocalDate} of the task.
     */
    public LocalDate getFromDate() {
        return this.fromDate;
    }

    /**
     * Returns the end {@code LocalDate} of the task.
     *
     * @return The end {@code LocalDate} of the task.
     */
    public LocalDate getToDate() {
        return this.toDate;
    }

    /**
     * Returns the status icon representing the task's completion status.
     * The icon is "X" if the task is completed, otherwise a space character is returned.
     *
     * @return The status icon representing the completion status.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Converts the task to a file-friendly format for saving.
     * This is an abstract method intended to be overridden by subclasses like {@link Deadline},
     * {@link Event}, and {@link Todo} to provide specific file formats.
     *
     * @return A string representing the task in a format suitable for saving to a file.
     */
    public String toFileFormat() {
        return "";
    }

    /**
     * Returns a string representation of the task, including its completion status and name.
     * The format is "[status icon] task name", where status icon is "X" if completed, or " " if not.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getTaskName();
    }
}
