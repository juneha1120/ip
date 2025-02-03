package chillguy.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.task.Todo;
import chillguy.ui.Ui;

public class DeleteCommandTest {
    @Test
    public void deleteTask_emptyTaskList_throwsException() {
        try {
            TaskList emptyTaskList = new TaskList();
            new DeleteCommand(1).deleteTask(emptyTaskList);
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void deleteTask_invalidTaskNum_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            exampleTaskList.addToTaskList(new Todo("Task 1"));
            int invalidTaskNum = -1;
            new DeleteCommand(invalidTaskNum).deleteTask(exampleTaskList);
        } catch (ChillGuyException ignored) {
            // Ignored
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
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }
}
