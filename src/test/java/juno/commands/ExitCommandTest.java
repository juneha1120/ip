package juno.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import juno.task.Todo;

public class ExitCommandTest {
    @Test
    public void isExit_exitCommand_returnsTrue() {
        Command exitCommand = new ExitCommand();
        assertTrue(ExitCommand.isExit(exitCommand));
    }

    @Test
    public void isExit_nonExitCommand_returnsFalse() {
        Command nonExitCommand = new AddCommand(new Todo("Task 1"));
        assertFalse(ExitCommand.isExit(nonExitCommand));
    }
}
