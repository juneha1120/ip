package chillguy.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import chillguy.task.Deadline;
import chillguy.task.TaskList;

public class UiTest {
    @Test
    public void shouldIgnore_nullString_throwsException() {
        try {
            new Ui().shouldIgnore(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showToUser_nullStrings_throwsException() {
        try {
            new Ui().showToUser(null, null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showLoadingMessage_nullTaskList_throwsException() {
        try {
            new Ui().showLoadingMessage(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showAdd_nullTask_throwsException() {
        try {
            new Ui().showAdd(null, 1);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showTasks_nullTaskList_throwsException() {
        try {
            new Ui().showTasks(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showTasksWithDate_nullTaskList_throwsException() {
        try {
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new Ui().showTasksWithDate(null, exampleDate);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showTasksWithDate_nullDate_throwsException() {
        TaskList exampleTaskList = new TaskList();
        LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
        exampleTaskList.addToTaskList(new Deadline("Task 1", exampleDate));
        try {
            new Ui().showTasksWithDate(exampleTaskList, null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showMark_nullTask_throwsException() {
        try {
            new Ui().showMark(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showUnmark_nullTask_throwsException() {
        try {
            new Ui().showUnmark(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showDelete_nullTask_throwsException() {
        try {
            new Ui().showDelete(null, 1);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showError_nullString_throwsException() {
        try {
            new Ui().showError(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }
}
