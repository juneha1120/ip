package juno.commands;

import juno.exceptions.JunoException;

import juno.task.Task;
import juno.task.TaskList;
import juno.task.Deadline;
import juno.task.Event;

import juno.storage.Storage;

import juno.ui.Ui;

import java.time.LocalDate;

import java.util.LinkedHashMap;
import java.util.Map;

import static juno.enums.ErrorType.LIST_WITH_DATE_ERROR;

public class ShowTasksWithDateCommand extends Command {
    public static final String COMMAND_WORD = "show tasks on";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : shows list of tasks on specified date.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " 01/01/1000";
    private final LocalDate date;


    public ShowTasksWithDateCommand(LocalDate date) {
        this.date = date;
    }

    public TaskList getTasksOnDate(TaskList taskList, LocalDate date) throws JunoException {
        Map<Integer, Task> taskListOriginal = taskList.getTaskList();
        Map<Integer, Task> taskListOnDate = new LinkedHashMap<>();
        int taskCount = 0;

        for (Task task : taskListOriginal.values()) {
            if (task instanceof Deadline deadline) {
                if (deadline.getByDate().equals(date)) {
                    taskListOnDate.put(++taskCount, task);
                }
            } else if (task instanceof Event event) {
                if (event.getFromDate().equals(date) || event.getToDate().equals(date)) {
                    taskListOnDate.put(++taskCount, task);
                }
            }
        }

        if (taskListOnDate.isEmpty()) {
            throw new JunoException(LIST_WITH_DATE_ERROR);
        }

        return new TaskList(taskListOnDate);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException {
        TaskList taskListOnDate = this.getTasksOnDate(taskList, this.date);
        ui.showTasksWithDate(taskListOnDate, this.date);
    }
}
