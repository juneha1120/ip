package chillguy.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.TextUi;

public class ShowTasksCommandTest {
    @Test
    public void execute_nullTaskList_throwsException() {
        TaskList nullTaskList = null;
        assertThrows(AssertionError.class, ()
                -> new ShowTasksCommand().execute(nullTaskList, new Storage(Storage.EXAMPLE), new TextUi()));
    }
}
