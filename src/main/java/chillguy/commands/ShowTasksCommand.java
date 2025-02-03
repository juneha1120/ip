package chillguy.commands;

import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.Ui;

/**
 * Represents a command to show a list of all tasks in the task list.
 * <p>
 * The {@code ShowTasksCommand} class is responsible for displaying all the tasks in the {@link TaskList}.
 * It triggers the {@link Ui} to show the entire task list to the user.
 */
public class ShowTasksCommand extends Command {
    public static final String COMMAND_WORD = "show tasks";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : shows list of added tasks.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD;

    /**
     * Executes the show tasks command by displaying all tasks in the {@link TaskList} through the {@link Ui}.
     *
     * @param taskList the list of tasks to be shown.
     * @param storage the storage system (unused in this command).
     * @param ui the user interface to display the task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTasks(taskList);
    }
}
