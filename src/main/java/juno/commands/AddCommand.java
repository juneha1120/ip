package juno.commands;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.Task;
import juno.task.TaskList;
import juno.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 * <p>
 * The {@code AddCommand} class is responsible for adding a {@link Task} to the {@link TaskList}, saving the updated
 * task list to the {@link Storage}, and displaying the corresponding confirmation message through the {@link Ui}.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task to be added.
     *
     * @param task the task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the provided {@link TaskList}, saving the updated task
     * list to {@link Storage}, and showing the updated task count through the {@link Ui}.
     *
     * @param taskList the list of tasks to which the task will be added.
     * @param storage the storage system to save the updated task list.
     * @param ui the user interface to display the confirmation message.
     * @throws JunoException if an error occurs while adding the task or saving the task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException {
        taskList.addToTaskList(task);
        storage.saveTasks(taskList);
        ui.showAdd(task, taskList.getTaskCount());
    }
}
