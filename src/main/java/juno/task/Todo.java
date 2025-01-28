package juno.task;

import static juno.commands.Command.EXAMPLE_PREFIX;

public class Todo extends Task {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : adds task without any date/time to task list.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " study CS2103T";

    public Todo(String taskName) {
        super(taskName);
    }

    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + this.getTaskName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}