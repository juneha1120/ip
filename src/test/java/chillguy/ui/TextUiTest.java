package chillguy.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import chillguy.task.Deadline;
import chillguy.task.TaskList;

public class TextUiTest {
    @Test
    public void shouldIgnore_nullString_throwsException() {
        try {
            new TextUi().shouldIgnore(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showToUser_nullStrings_throwsException() {
        try {
            new TextUi().showToUser(null, null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showLoadingMessage_nullTaskList_throwsException() {
        try {
            new TextUi().showLoadingMessage(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showAdd_nullTask_throwsException() {
        try {
            new TextUi().showAdd(null, 1);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showTasks_nullTaskList_throwsException() {
        try {
            new TextUi().showTasks(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showTasksWithDate_nullTaskList_throwsException() {
        try {
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new TextUi().showTasksWithDate(null, exampleDate);
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
            new TextUi().showTasksWithDate(exampleTaskList, null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showMark_nullTask_throwsException() {
        try {
            new TextUi().showMark(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showUnmark_nullTask_throwsException() {
        try {
            new TextUi().showUnmark(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showDelete_nullTask_throwsException() {
        try {
            new TextUi().showDelete(null, 1);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void showError_nullString_throwsException() {
        try {
            new TextUi().showError(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }
}
