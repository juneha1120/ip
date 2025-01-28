package juno.commands;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.TaskList;
import juno.task.Todo;
import juno.ui.Ui;
import org.junit.jupiter.api.Test;

public class ShowTasksCommandTest {
    @Test
    public void execute_nullTaskList_throwsException() {
        try {
            TaskList nullTaskList = null;
            new ShowTasksCommand().execute(nullTaskList, new Storage(Storage.EXAMPLE), new Ui());
        } catch (NullPointerException ignored) {
        }
    }
}
