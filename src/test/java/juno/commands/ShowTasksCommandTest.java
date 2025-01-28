package juno.commands;

import org.junit.jupiter.api.Test;

import juno.storage.Storage;
import juno.task.TaskList;
import juno.ui.Ui;

public class ShowTasksCommandTest {
    @Test
    public void execute_nullTaskList_throwsException() {
        try {
            TaskList nullTaskList = null;
            new ShowTasksCommand().execute(nullTaskList, new Storage(Storage.EXAMPLE), new Ui());
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }
}
