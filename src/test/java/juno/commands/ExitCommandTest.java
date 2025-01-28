package juno.commands;

import juno.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExitCommandTest {
    @Test
    public void isExit_ExitCommand_returnsTrue() {
        Command exitCommand = new ExitCommand();
        assertTrue(ExitCommand.isExit(exitCommand));
    }

    @Test
    public void isExit_nonExitCommand_returnsFalse() {
        Command nonExitCommand = new AddCommand(new Todo("Task 1"));
        assertFalse(ExitCommand.isExit(nonExitCommand));
    }
}
