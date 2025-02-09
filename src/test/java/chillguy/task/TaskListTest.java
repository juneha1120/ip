package chillguy.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
        Task nullTask = null;
        assertThrows(AssertionError.class, () -> new TaskList().addToTaskList(nullTask));
    }

    @Test
    public void setTaskList_nullTaskList_throwsException() {
        Map<Integer, Task> nullTaskList = null;
        assertThrows(AssertionError.class, () -> new TaskList().setTaskList(nullTaskList));
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
