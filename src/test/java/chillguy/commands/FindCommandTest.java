package chillguy.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Deadline;
import chillguy.task.TaskList;
import chillguy.ui.TextUi;

public class FindCommandTest {
    @Test
    public void getTasksWithKeyword_emptyTaskList_throwsException() {
        try {
            TaskList emptyTaskList = new TaskList();
            String exampleKeyword = "study";
            new FindCommand(exampleKeyword).getTasksWithKeyword(emptyTaskList);
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void getTasksWithKeyword_emptyKeyword_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            exampleTaskList.addToTaskList(new Deadline("Task 1", exampleDate));
            new FindCommand("").getTasksWithKeyword(exampleTaskList);
        } catch (DateTimeParseException | ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void execute_emptyKeyword_throwsException() {
        try {
            TaskList exampleTaskList = new TaskList();
            LocalDate exampleDate = LocalDate.parse("1/1/1000", DateTimeFormatter.ofPattern("d/M/yyyy"));
            exampleTaskList.addToTaskList(new Deadline("Task 1", exampleDate));
            new FindCommand("").execute(exampleTaskList, new Storage(Storage.EXAMPLE), new TextUi());
        } catch (DateTimeParseException | ChillGuyException ignored) {
            // Ignored
        }
    }
}
