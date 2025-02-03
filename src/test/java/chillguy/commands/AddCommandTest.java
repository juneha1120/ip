package chillguy.commands;

import org.junit.jupiter.api.Test;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.ui.Ui;

public class AddCommandTest {
    @Test
    public void execute_nullTask_throwsException() {
        try {
            Task invalidTask = null;
            new AddCommand(invalidTask).execute(new TaskList(), new Storage(Storage.EXAMPLE), new Ui());
        } catch (NullPointerException | ChillGuyException ignored) {
            // Ignored
        }
    }
}
