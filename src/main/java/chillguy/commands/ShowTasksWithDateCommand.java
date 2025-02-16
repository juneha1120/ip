package chillguy.commands;

import static chillguy.enums.ErrorType.LIST_WITH_DATE_ERROR;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Deadline;
import chillguy.task.Event;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to show a list of tasks on a specified date.
 * <p>
 * The {@code ShowTasksWithDateCommand} class is responsible for filtering tasks based on the specified date.
 * It checks both {@link Deadline} and {@link Event} tasks in the {@link TaskList} and retrieves those that
 * match the given date. If no tasks are found for the date, a {@link ChillGuyException} is thrown.
 * After retrieving the tasks for the specified date, the task list is displayed through the {@link TextUi}.
 */
public class ShowTasksWithDateCommand extends Command {
    public static final String COMMAND_WORD = "show";
    public static final String COMMAND_PHRASE = "show tasks on";
    public static final String COMMAND_DESCRIPTION = COMMAND_PHRASE + " : shows list of tasks on specified date.\n"
            + EXAMPLE_PREFIX + COMMAND_PHRASE + " 01/01/1000";
    private final LocalDate date;

    /**
     * Constructs a {@code ShowTasksWithDateCommand} for the specified date.
     *
     * @param date the date to filter tasks by.
     */
    public ShowTasksWithDateCommand(LocalDate date) {
        assert date != null : "Date cannot be null";
        this.date = date;
    }

    /**
     * Retrieves the tasks on the specified date from the {@link TaskList}.
     * <p>
     * The method checks for both {@link Deadline} and {@link Event} tasks that either have the specified date
     * as their deadline or occur on the specified date. If no tasks are found, a {@link ChillGuyException} is thrown.
     *
     * @param taskList the task list to search through.
     * @return a {@link TaskList} containing the tasks on the specified date.
     * @throws ChillGuyException if no tasks are found on the specified date.
     */
    public TaskList getTasksOnDate(TaskList taskList) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";

        Map<Integer, Task> taskListOriginal = taskList.getTaskList();
        Map<Integer, Task> taskListOnDate = new LinkedHashMap<>();
        int taskCount = 0;

        for (Task task : taskListOriginal.values()) {
            if (task instanceof Deadline deadline) {
                if (deadline.getByDate().equals(this.date)) {
                    taskListOnDate.put(++taskCount, task);
                }
            } else if (task instanceof Event event) {
                if (event.getFromDate().equals(this.date) || event.getToDate().equals(this.date)) {
                    taskListOnDate.put(++taskCount, task);
                }
            }
        }

        if (taskListOnDate.isEmpty()) {
            throw new ChillGuyException(LIST_WITH_DATE_ERROR);
        }

        return new TaskList(taskListOnDate);
    }

    /**
     * Executes the show tasks on date command by filtering and displaying the tasks for the specified date.
     *
     * @param taskList the list of tasks to be searched through.
     * @param storage the storage system (unused in this command).
     * @param textUi the user interface to display the filtered task list.
     * @throws ChillGuyException if no tasks are found for the specified date.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert textUi != null : "Text UI cannot be null";

        TaskList taskListOnDate = this.getTasksOnDate(taskList);
        textUi.showTasksWithDate(taskListOnDate, this.date);
    }

    /**
     * Executes the show tasks on date command by filtering and displaying the tasks for the specified date.
     *
     * @param taskList the list of tasks to be searched through.
     * @param storage the storage system (unused in this command).
     * @param graphicalUi the user interface to return the filtered task list.
     * @throws ChillGuyException if no tasks are found for the specified date.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert graphicalUi != null : "Graphical UI cannot be null";

        TaskList taskListOnDate = this.getTasksOnDate(taskList);
        graphicalUi.respondWithTasksOnDate(taskListOnDate, this.date);
    }
}
