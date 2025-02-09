package chillguy.commands;

import static chillguy.enums.ErrorType.MARK_ERROR;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to mark a specified task as done.
 * <p>
 * The {@code MarkCommand} class is responsible for changing the status of a task to "done" based on its index in the
 * {@link TaskList}. If the task does not exist or is already marked as done, a {@link ChillGuyException} will be
 * thrown. After marking the task, the task list is saved to {@link Storage} and the updated task is displayed through
 * the {@link TextUi}.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : marks specified task as done.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " 3";
    private final int taskNum;

    /**
     * Constructs a {@code MarkCommand} to mark the specified task as done.
     *
     * @param taskNum the index of the task to be marked as done.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the mark command by marking the specified task as done.
     * <p>
     * If the task does not exist or is already marked as done, a {@link ChillGuyException} will be thrown. Otherwise,
     * the task will be marked as done, the task list will be saved to {@link Storage}, and the updated task will
     * be displayed through the {@link TextUi}.
     *
     * @param taskList the list of tasks to be modified.
     * @param storage the storage system to save the updated task list.
     * @param textUi the user interface to display the task status update.
     * @throws ChillGuyException if the task does not exist or is already marked as done.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) throws ChillGuyException {
        Task curr = taskList.getTaskList().get(taskNum);
        if (curr == null) {
            throw new ChillGuyException(MARK_ERROR, false, taskNum);
        } else if (curr.isDone()) {
            throw new ChillGuyException(MARK_ERROR, true, taskNum);
        }

        curr.mark();
        storage.saveTasks(taskList);
        textUi.showMark(curr);
    }

    /**
     * Executes the mark command by marking the specified task as done.
     * <p>
     * If the task does not exist or is already marked as done, a {@link ChillGuyException} will be thrown. Otherwise,
     * the task will be marked as done, the task list will be saved to {@link Storage}, and the updated task will
     * be displayed through the {@link GraphicalUi}.
     *
     * @param taskList the list of tasks to be modified.
     * @param storage the storage system to save the updated task list.
     * @param graphicalUi the user interface to return the task status update.
     * @throws ChillGuyException if the task does not exist or is already marked as done.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        Task curr = taskList.getTaskList().get(taskNum);
        if (curr == null) {
            throw new ChillGuyException(MARK_ERROR, false, taskNum);
        } else if (curr.isDone()) {
            throw new ChillGuyException(MARK_ERROR, true, taskNum);
        }

        curr.mark();
        storage.saveTasks(taskList);
        graphicalUi.respondWithMarkMessage(curr);
    }
}
