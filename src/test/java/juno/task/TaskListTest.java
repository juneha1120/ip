package juno.task;

import org.junit.jupiter.api.Test;
import java.util.Map;

public class TaskListTest {
    @Test
    public void taskList_nullTaskList_throwsException() {
        try {
            Map<Integer, Task> nullTaskList = null;
            new TaskList(nullTaskList);
        } catch (NullPointerException ignored){
        }
    }

    @Test
    public void addToTaskList_nullTask_throwsException() {
        try {
            Task nullTask = null;
            new TaskList().addToTaskList(nullTask);
        } catch (NullPointerException ignored){
        }
    }

    @Test
    public void setTaskList_nullTaskList_throwsException() {
        try {
            Map<Integer, Task> nullTaskList = null;
            new TaskList().setTaskList(nullTaskList);
        } catch (NullPointerException ignored){
        }
    }

    @Test
    public void getStringTaskList_nullTaskList_throwsException() {
        try {
            Map<Integer, Task> nullTaskList = null;
            new TaskList(nullTaskList).getStringTaskList();
        } catch (NullPointerException ignored){
        }
    }
}
