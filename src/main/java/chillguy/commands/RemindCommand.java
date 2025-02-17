package chillguy.commands;

import static chillguy.enums.ErrorType.REMINDERS_ERROR;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import chillguy.enums.TaskType;
import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to get reminders of specific type of task
 * <p>
 * The {@code RemindCommand} class is responsible for reminding a specific type of task in the {@link TaskList}.
 * If the specified type of task does not exist, or is not due today a {@link ChillGuyException} will be thrown.
 * After retrieving the reminders, the task list is displayed through the {@link TextUi} or {@link GraphicalUi}.
 */
public class RemindCommand extends Command {
    public static final String COMMAND_WORD = "remind";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + ": shows list of specified type due today.\n"
            + EXAMPLE_PREFIX + COMMAND_WORD + " todo";
    private final TaskType type;

    /**
     * Constructs a {@code RemindCommand} for the specified task type.
     *
     * @param type the type to filter tasks by.
     */
    public RemindCommand(TaskType type) {
        assert type != null : "Task type cannot be null";
        this.type = type;
    }

    /**
     * Retrieves the list of tasks from the {@code taskList} that contain the specified task type due today.
     * <p>
     * If the specified type of task does not exist, or is not due today a {@link ChillGuyException} will be thrown.
     *
     * @param taskList The list of tasks to search through.
     * @return A {@link TaskList} containing the tasks that contain the specified task type due today.
     * @throws ChillGuyException If the specified type of task does not exist, or is not due today
     */
    public TaskList getReminders(TaskList taskList) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";

        Map<Integer, Task> taskListOriginal = taskList.getTaskList();
        Map<Integer, Task> reminders = new LinkedHashMap<>();
        int reminderCount = 0;

        for (Task task : taskListOriginal.values()) {
            if (task.getType() == TaskType.TODO && task.getType().equals(this.type)) {
                reminders.put(++reminderCount, task);
            } else if (task.getType() == TaskType.DEADLINE && task.getType().equals(this.type)) {
                if (task.getByDate().isEqual(LocalDate.now())) {
                    reminders.put(++reminderCount, task);
                }
            } else if (task.getType() == TaskType.EVENT && task.getType().equals(this.type)) {
                if ((task.getFromDate().isBefore(LocalDate.now()) && task.getToDate().isAfter(LocalDate.now()))
                    || task.getFromDate().isEqual(LocalDate.now()) || task.getToDate().isEqual(LocalDate.now())) {
                    reminders.put(++reminderCount, task);
                }
            }
        }

        if (reminders.isEmpty()) {
            throw new ChillGuyException(REMINDERS_ERROR);
        }

        return new TaskList(reminders);
    }

    /**
     * Executes the reminder command by filtering and displaying the tasks for the specified task type.
     *
     * @param taskList the list of tasks to be searched through.
     * @param storage the storage system (unused in this command).
     * @param textUi the user interface to display the filtered task list.
     * @throws ChillGuyException if no reminders are found for the task type.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert textUi != null : "Text UI cannot be null";

        TaskList reminders = this.getReminders(taskList);
        textUi.showReminders(reminders, this.type);
    }

    /**
     * Executes the reminder command by filtering and displaying the tasks for the specified task type.
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

        TaskList reminders = this.getReminders(taskList);
        graphicalUi.respondWithReminders(reminders, this.type);
    }
}
