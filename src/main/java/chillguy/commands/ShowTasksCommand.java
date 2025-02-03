package chillguy.commands;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to show a list of all tasks in the task list.
 * <p>
 * The {@code ShowTasksCommand} class is responsible for displaying all the tasks in the {@link TaskList}.
 * It triggers the {@link TextUi} to show the entire task list to the user.
 */
public class ShowTasksCommand extends Command {
    public static final String COMMAND_WORD = "show tasks";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : shows list of added tasks.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD;

    /**
     * Executes the show tasks command by displaying all tasks in the {@link TaskList} through the {@link TextUi}.
     *
     * @param taskList the list of tasks to be shown.
     * @param storage the storage system (unused in this command).
     * @param textUi the user interface to display the task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) {
        textUi.showTasks(taskList);
    }

    /**
     * Executes the show tasks command by displaying all tasks in the {@link TaskList} through the {@link GraphicalUi}.
     *
     * @param taskList the list of tasks to be shown.
     * @param storage the storage system (unused in this command).
     * @param graphicalUi the user interface to return the task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        graphicalUi.appendTasks(taskList);
    }
}
