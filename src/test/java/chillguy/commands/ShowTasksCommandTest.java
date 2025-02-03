package chillguy.commands;

import org.junit.jupiter.api.Test;

import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.TextUi;

public class ShowTasksCommandTest {
    @Test
    public void execute_nullTaskList_throwsException() {
        try {
            TaskList nullTaskList = null;
            new ShowTasksCommand().execute(nullTaskList, new Storage(Storage.EXAMPLE), new TextUi());
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }
}
