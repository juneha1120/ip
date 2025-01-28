package command;

import exceptions.JunoException;

import task.Task;
import task.TaskList;

import storage.Storage;

import ui.Ui;

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
