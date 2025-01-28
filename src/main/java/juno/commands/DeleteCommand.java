package juno.commands;

import static juno.enums.ErrorType.DELETE_ERROR;

import java.util.LinkedHashMap;
import java.util.Map;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.Task;
import juno.task.TaskList;
import juno.ui.Ui;

/**
 * Represents a command to delete a specified task from the task list.
 * <p>
 * The {@code DeleteCommand} class is responsible for removing a task from the {@link TaskList} based on its
 * index and then renumbering the remaining tasks to ensure correct indexing. If the task index is invalid, a
 * {@link JunoException} is thrown. After successfully deleting the task, the updated task list is saved to
 * {@link Storage}, and a confirmation message is shown through the {@link Ui}.
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
     * Otherwise, a {@link JunoException} will be thrown.
     *
     * @param taskList the task list from which the task will be deleted.
     * @return the deleted task.
     * @throws JunoException if the task does not exist in the list.
     */
    public Task deleteTask(TaskList taskList) throws JunoException {
        Task deletedTask;
        if (taskList.getTaskList().containsKey(this.taskNum)) {
            deletedTask = taskList.getTaskList().remove(this.taskNum);
            this.renumberTasks(taskList);
        } else {
            throw new JunoException(DELETE_ERROR, true, this.taskNum);
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
     * saving the updated task list to the {@link Storage}, and displaying the result through the {@link Ui}.
     *
     * @param taskList the list of tasks to be modified.
     * @param storage the storage system to save the updated task list.
     * @param ui the user interface to display the deletion confirmation.
     * @throws JunoException if an error occurs while deleting the task or saving the task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException {
        Task deletedTask = this.deleteTask(taskList);
        storage.saveTasks(taskList);
        ui.showDelete(deletedTask, taskList.getTaskCount());
    }
}
