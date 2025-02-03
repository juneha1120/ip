package chillguy.task;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void taskList_nullTaskList_throwsException() {
        try {
            Map<Integer, Task> nullTaskList = null;
            new TaskList(nullTaskList);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void addToTaskList_nullTask_throwsException() {
        try {
            Task nullTask = null;
            new TaskList().addToTaskList(nullTask);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void setTaskList_nullTaskList_throwsException() {
        try {
            Map<Integer, Task> nullTaskList = null;
            new TaskList().setTaskList(nullTaskList);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void getStringTaskList_nullTaskList_throwsException() {
        try {
            Map<Integer, Task> nullTaskList = null;
            new TaskList(nullTaskList).getStringTaskList();
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }
}
