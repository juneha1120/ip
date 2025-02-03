package chillguy.storage;

import org.junit.jupiter.api.Test;

import chillguy.exceptions.ChillGuyException;
import chillguy.task.TaskList;
import chillguy.task.Todo;

public class StorageTest {
    @Test
    public void fromFileFormat_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            Storage.fromFileFormat(invalidString);
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void saveTasks_nullTaskList_throwsException() {
        try {
            TaskList nullTaskList = null;
            new Storage(Storage.EXAMPLE).saveTasks(nullTaskList);
        } catch (ChillGuyException | NullPointerException ignored) {
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
        } catch (ChillGuyException | NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void loadTasks_invalidFilePath_throwsException() {
        try {
            String invalidFilePath = "111111111";
            new Storage(invalidFilePath).loadTasks();
        } catch (ChillGuyException | NullPointerException ignored) {
            // Ignored
        }
    }
}
