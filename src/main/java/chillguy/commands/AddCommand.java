package chillguy.commands;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to add a task to the task list.
 * <p>
 * The {@code AddCommand} class is responsible for adding a {@link Task} to the {@link TaskList}, saving the updated
 * task list to the {@link Storage}, and displaying the corresponding confirmation message through the {@link TextUi}.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an {@code AddCommand} with the specified task to be added.
     *
     * @param task the task to be added to the task list.
     */
    public AddCommand(Task task) {
        assert task != null : "Task cannot be null";
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the provided {@link TaskList}, saving the updated task
     * list to {@link Storage}, and showing the updated task count through the {@link TextUi}.
     *
     * @param taskList the list of tasks to which the task will be added.
     * @param storage the storage system to save the updated task list.
     * @param textUi the user interface to display the confirmation message.
     * @throws ChillGuyException if an error occurs while adding the task or saving the task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert textUi != null : "Text UI cannot be null";

        taskList.addToTaskList(task);
        storage.saveTasks(taskList);
        textUi.showAdd(task, taskList.getTaskCount());
    }

    /**
     * Executes the command by adding the task to the provided {@link TaskList}, saving the updated task
     * list to {@link Storage}, and showing the updated task count through the {@link GraphicalUi}.
     *
     * @param taskList the list of tasks to which the task will be added.
     * @param storage the storage system to save the updated task list.
     * @param graphicalUi the user interface to return the confirmation message.
     * @throws ChillGuyException if an error occurs while adding the task or saving the task list.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert graphicalUi != null : "Graphical UI cannot be null";

        taskList.addToTaskList(task);
        storage.saveTasks(taskList);
        graphicalUi.appendAdd(task, taskList.getTaskCount());
    }
}
