package chillguy.commands;

import static chillguy.enums.ErrorType.DELETE_ERROR;

import java.util.LinkedHashMap;
import java.util.Map;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to delete a specified task from the task list.
 * <p>
 * The {@code DeleteCommand} class is responsible for removing a task from the {@link TaskList} based on its
 * index and then renumbering the remaining tasks to ensure correct indexing. If the task index is invalid, a
 * {@link ChillGuyException} is thrown. After successfully deleting the task, the updated task list is saved to
 * {@link Storage}, and a confirmation message is shown through the {@link TextUi}.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : delete specified task.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " 3";
    private final int taskNum;

    /**
     * Constructs a {@code DeleteCommand} to delete the specified task.
     *
     * @param taskNum the index of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task at the specified index from the {@link TaskList}.
     * <p>
     * If the task exists in the list, it will be removed and the remaining tasks will be renumbered.
     * Otherwise, a {@link ChillGuyException} will be thrown.
     *
     * @param taskList the task list from which the task will be deleted.
     * @return the deleted task.
     * @throws ChillGuyException if the task does not exist in the list.
     */
    public Task deleteTask(TaskList taskList) throws ChillGuyException {
        Task deletedTask;
        if (taskList.getTaskList().containsKey(this.taskNum)) {
            deletedTask = taskList.getTaskList().remove(this.taskNum);
            this.renumberTasks(taskList);
        } else {
            throw new ChillGuyException(DELETE_ERROR, true, this.taskNum);
        }

        return deletedTask;
    }

    /**
     * Renumbers the tasks in the {@link TaskList} after a task has been deleted.
     * <p>
     * The task list is renumbered starting from 1, and the task count is updated accordingly.
     *
     * @param taskList the task list to be renumbered.
     */
    public void renumberTasks(TaskList taskList) {
        Map<Integer, Task> newTaskList = new LinkedHashMap<>();
        int newTaskCount = 0;

        for (Map.Entry<Integer, Task> entry : taskList.getTaskList().entrySet()) {
            newTaskList.put(++newTaskCount, entry.getValue());
        }

        taskList.setTaskList(newTaskList);
        taskList.updateTaskCount();
    }

    /**
     * Executes the delete command by deleting the specified task from the {@link TaskList},
     * saving the updated task list to the {@link Storage}, and displaying the result through the {@link TextUi}.
     *
     * @param taskList the list of tasks to be modified.
     * @param storage the storage system to save the updated task list.
     * @param textUi the user interface to display the deletion confirmation.
     * @throws ChillGuyException if an error occurs while deleting the task or saving the task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) throws ChillGuyException {
        Task deletedTask = this.deleteTask(taskList);
        storage.saveTasks(taskList);
        textUi.showDelete(deletedTask, taskList.getTaskCount());
    }

    /**
     * Executes the delete command by deleting the specified task from the {@link TaskList},
     * saving the updated task list to the {@link Storage}, and displaying the result through the {@link GraphicalUi}.
     *
     * @param taskList the list of tasks to be modified.
     * @param storage the storage system to save the updated task list.
     * @param graphicalUi the user interface to return the deletion confirmation.
     * @throws ChillGuyException if an error occurs while deleting the task or saving the task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        Task deletedTask = this.deleteTask(taskList);
        storage.saveTasks(taskList);
        graphicalUi.appendDelete(deletedTask, taskList.getTaskCount());
    }
}
