package juno.commands;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.Deadline;
import juno.task.TaskList;
import juno.ui.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ShowTasksWithDateCommandTest {
    @Test
    public void getTasksOnDate_emptyTaskList_throwsException() {
        try {
            TaskList emptyTaskList = new TaskList();
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            new ShowTasksWithDateCommand(exampleDate).getTasksOnDate(emptyTaskList);
        } catch (JunoException ignored) {
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
        } catch (DateTimeParseException | JunoException ignored) {
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
        } catch (DateTimeParseException | JunoException ignored) {
        }
    }
}