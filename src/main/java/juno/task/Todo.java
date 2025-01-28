package juno.task;

import static juno.commands.Command.EXAMPLE_PREFIX;

/**
 * Represents a {@link Task} that does not have any specific date/time associated with it.
 * This class extends the {@link Task} class and encapsulates details about a task
 * without any additional date or time information.
 */
public class Todo extends Task {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : adds task without any date/time to task list.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " study CS2103T";

    /**
     * Constructs a {@code Todo} task with the specified task name. The task is marked as not done by default.
     *
     * @param taskName The name of the todo task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a {@code Todo} task with the specified task name and completion status.
     *
     * @param taskName The name of the todo task.
     * @param isDone The completion status of the todo task (true if completed, false otherwise).
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Converts the {@code Todo} to a file-friendly format for saving. The format includes the task type ("T"),
     * completion status, and task name.
     *
     * @return A string representing the todo task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + this.getTaskName();
    }

    /**
     * Returns a string representation of the {@code Todo} task, including its status.
     *
     * @return A string representing the {@code Todo} task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
