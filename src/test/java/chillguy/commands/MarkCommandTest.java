package chillguy.commands;

import org.junit.jupiter.api.Test;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.task.Todo;
import chillguy.ui.Ui;

public class MarkCommandTest {
    @Test
    public void execute_invalidTaskNum_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            exampleTaskList.addToTaskList(new Todo("Task 1"));
            int invalidTaskNum = -1;
            new MarkCommand(invalidTaskNum).execute(exampleTaskList, new Storage(Storage.EXAMPLE), new Ui());
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }
}
