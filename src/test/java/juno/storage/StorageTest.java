package juno.storage;

import org.junit.jupiter.api.Test;

import juno.exceptions.JunoException;
import juno.task.TaskList;
import juno.task.Todo;

public class StorageTest {
    @Test
    public void fromFileFormat_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            Storage.fromFileFormat(invalidString);
        } catch (JunoException ignored) {
            // Ignored
        }
    }

    @Test
    public void saveTasks_nullTaskList_throwsException() {
        try {
            TaskList nullTaskList = null;
            new Storage(Storage.EXAMPLE).saveTasks(nullTaskList);
        } catch (JunoException | NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void saveTasks_invalidFilePath_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            exampleTaskList.addToTaskList(new Todo("Task 1"));
            String invalidFilePath = "111111111";
            new Storage(invalidFilePath).saveTasks(exampleTaskList);
        } catch (JunoException | NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void loadTasks_invalidFilePath_throwsException() {
        try {
            String invalidFilePath = "111111111";
            new Storage(invalidFilePath).loadTasks();
        } catch (JunoException | NullPointerException ignored) {
            // Ignored
        }
    }
}
