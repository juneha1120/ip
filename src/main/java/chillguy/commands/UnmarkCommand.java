package chillguy.commands;

import static chillguy.enums.ErrorType.UNMARK_ERROR;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to unmark a specified task as not done.
 * <p>
 * The {@code UnmarkCommand} class is responsible for changing the status of a task to "not done" based on its index in
 * the {@link TaskList}. If the task does not exist or is already unmarked, a {@link ChillGuyException} will be thrown.
 * After unmarking the task, the task list is saved to {@link Storage} and the updated task is displayed through the
 * {@link TextUi}.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : unmarks specified task.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " 3";
    private final int taskNum;

    /**
     * Constructs a {@code UnmarkCommand} to unmark the specified task.
     *
     * @param taskNum the index of the task to be unmarked.
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the unmark command by unmarking the specified task.
     * <p>
     * If the task does not exist or is not marked as done, a {@link ChillGuyException} will be thrown. Otherwise,
     * the task will be unmarked, the task list will be saved to {@link Storage}, and the updated task will
     * be displayed through the {@link TextUi}.
     *
     * @param taskList the list of tasks to be modified.
     * @param storage the storage system to save the updated task list.
     * @param textUi the user interface to display the task status update.
     * @throws ChillGuyException if the task does not exist or is not marked as done.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) throws ChillGuyException {
        Task curr = taskList.getTaskList().get(taskNum);
        if (curr == null) {
            throw new ChillGuyException(UNMARK_ERROR, true, taskNum);
        } else if (!curr.isDone()) {
            throw new ChillGuyException(UNMARK_ERROR, false, taskNum);
        }
        curr.unmark();
        storage.saveTasks(taskList);
        textUi.showUnmark(curr);
    }

    /**
     * Executes the unmark command by unmarking the specified task.
     * <p>
     * If the task does not exist or is not marked as done, a {@link ChillGuyException} will be thrown. Otherwise,
     * the task will be unmarked, the task list will be saved to {@link Storage}, and the updated task will
     * be displayed through the {@link GraphicalUi}.
     *
     * @param taskList the list of tasks to be modified.
     * @param storage the storage system to save the updated task list.
     * @param graphicalUi the user interface to display the task status update.
     * @throws ChillGuyException if the task does not exist or is not marked as done.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        Task curr = taskList.getTaskList().get(taskNum);
        if (curr == null) {
            throw new ChillGuyException(UNMARK_ERROR, true, taskNum);
        } else if (!curr.isDone()) {
            throw new ChillGuyException(UNMARK_ERROR, false, taskNum);
        }
        curr.unmark();
        storage.saveTasks(taskList);
        graphicalUi.appendUnmark(curr);
    }
}
