package chillguy.commands;

import org.junit.jupiter.api.Test;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.task.Todo;
import chillguy.ui.TextUi;

public class UnmarkCommandTest {
    @Test
    public void execute_invalidTaskNum_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            exampleTaskList.addToTaskList(new Todo("Task 1"));
            int invalidTaskNum = -1;
            new UnmarkCommand(invalidTaskNum).execute(exampleTaskList, new Storage(Storage.EXAMPLE), new TextUi());
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }
}
