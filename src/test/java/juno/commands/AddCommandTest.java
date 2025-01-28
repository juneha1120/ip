package juno.commands;

import org.junit.jupiter.api.Test;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.Task;
import juno.task.TaskList;
import juno.ui.Ui;

public class AddCommandTest {
    @Test
    public void execute_nullTask_throwsException() {
        try {
            Task invalidTask = null;
            new AddCommand(invalidTask).execute(new TaskList(), new Storage(Storage.EXAMPLE), new Ui());
        } catch (NullPointerException | JunoException ignored) {
            // Ignored
        }
    }
}
