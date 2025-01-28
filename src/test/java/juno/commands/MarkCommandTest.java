package juno.commands;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.TaskList;
import juno.task.Todo;
import juno.ui.Ui;
import org.junit.jupiter.api.Test;

public class MarkCommandTest {
    @Test
    public void execute_invalidTaskNum_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            exampleTaskList.addToTaskList(new Todo("Task 1"));
            int invalidTaskNum = -1;
            new MarkCommand(invalidTaskNum).execute(exampleTaskList, new Storage(Storage.EXAMPLE), new Ui());
        } catch (JunoException ignored) {
        }
    }
}
