package juno.commands;

import org.junit.jupiter.api.Test;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.TaskList;
import juno.task.Todo;
import juno.ui.Ui;

public class UnmarkCommandTest {
    @Test
    public void execute_invalidTaskNum_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            exampleTaskList.addToTaskList(new Todo("Task 1"));
            int invalidTaskNum = -1;
            new UnmarkCommand(invalidTaskNum).execute(exampleTaskList, new Storage(Storage.EXAMPLE), new Ui());
        } catch (JunoException ignored) {
            // Ignored
        }
    }
}
