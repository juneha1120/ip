package chillguy.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import chillguy.storage.Storage;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.ui.TextUi;

public class AddCommandTest {
    @Test
    public void execute_nullTask_throwsException() {
        Task invalidTask = null;
        assertThrows(AssertionError.class, ()
                -> new AddCommand(invalidTask).execute(new TaskList(), new Storage(Storage.EXAMPLE), new TextUi()));
    }
}
