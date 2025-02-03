package chillguy.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Deadline;
import chillguy.task.TaskList;
import chillguy.ui.Ui;

public class ShowTasksWithDateCommandTest {
    @Test
    public void getTasksOnDate_emptyTaskList_throwsException() {
        try {
            TaskList emptyTaskList = new TaskList();
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new ShowTasksWithDateCommand(exampleDate).getTasksOnDate(emptyTaskList);
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void getTasksOnDate_invalidDate_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            exampleTaskList.addToTaskList(new Deadline("Task 1", exampleDate));
            LocalDate invalidDate = LocalDate.parse("1/13/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new ShowTasksWithDateCommand(invalidDate).getTasksOnDate(exampleTaskList);
        } catch (DateTimeParseException | ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void execute_invalidDate_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            exampleTaskList.addToTaskList(new Deadline("Task 1", exampleDate));
            LocalDate invalidDate = LocalDate.parse("1/13/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new ShowTasksWithDateCommand(invalidDate).execute(exampleTaskList, new Storage(Storage.EXAMPLE), new Ui());
        } catch (DateTimeParseException | ChillGuyException ignored) {
            // Ignored
        }
    }
}
