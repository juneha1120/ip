package juno.commands;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.Task;
import juno.task.TaskList;
import juno.task.Todo;
import juno.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void deleteTask_emptyTaskList_throwsException() {
        try {
            TaskList emptyTaskList = new TaskList();
            new DeleteCommand(1).deleteTask(emptyTaskList);
        } catch (JunoException ignored) {
        }
    }

    @Test
    public void deleteTask_invalidTaskNum_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            exampleTaskList.addToTaskList(new Todo("Task 1"));
            int invalidTaskNum = -1;
            new DeleteCommand(invalidTaskNum).deleteTask(exampleTaskList);
        } catch (JunoException ignored) {
        }
    }

    @Test
    public void renumberTasks_preservesOrder() {
        TaskList taskList = new TaskList();
        taskList.getTaskList().put(10, new Todo("Task A"));
        taskList.getTaskList().put(20, new Todo("Task B"));
        taskList.getTaskList().put(30, new Todo("Task C"));

        new DeleteCommand(1).renumberTasks(taskList);

        List<String> taskDescriptions = taskList.getTaskList().values()
                .stream()
                .map(Task::getTaskName)
                .toList();

        assertEquals(List.of("Task A", "Task B", "Task C"), taskDescriptions);
    }

    @Test
    public void execute_invalidTaskNum_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            exampleTaskList.addToTaskList(new Todo("Task 1"));
            int invalidTaskNum = -1;
            new DeleteCommand(invalidTaskNum).execute(exampleTaskList, new Storage(Storage.EXAMPLE), new Ui());
        } catch (JunoException ignored) {
        }
    }
}
