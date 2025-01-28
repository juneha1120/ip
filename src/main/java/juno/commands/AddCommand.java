package juno.commands;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.Task;
import juno.task.TaskList;
import juno.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException {
        taskList.addToTaskList(task);
        storage.saveTasks(taskList);
        ui.showAdd(task, taskList.getTaskCount());
    }
}
