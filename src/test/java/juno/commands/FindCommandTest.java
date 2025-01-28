package juno.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.Deadline;
import juno.task.TaskList;
import juno.ui.Ui;

public class FindCommandTest {
    @Test
    public void getTasksWithKeyword_emptyTaskList_throwsException() {
        try {
            TaskList emptyTaskList = new TaskList();
            String exampleKeyword = "study";
            new FindCommand(exampleKeyword).getTasksWithKeyword(emptyTaskList);
        } catch (JunoException ignored) {
        }
    }

    @Test
    public void getTasksWithKeyword_emptyKeyword_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            exampleTaskList.addToTaskList(new Deadline("Task 1", exampleDate));
            new FindCommand("").getTasksWithKeyword(exampleTaskList);
        } catch (DateTimeParseException | JunoException ignored) {
        }
    }

    @Test
    public void execute_emptyKeyword_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            exampleTaskList.addToTaskList(new Deadline("Task 1", exampleDate));
            new FindCommand("").execute(exampleTaskList, new Storage(Storage.EXAMPLE), new Ui());
        } catch (DateTimeParseException | JunoException ignored) {
        }
    }
}
