package juno.command;

import juno.exceptions.JunoException;

import juno.task.Task;
import juno.task.TaskList;

import juno.storage.Storage;

import juno.ui.Ui;

import java.util.LinkedHashMap;
import java.util.Map;

import static juno.enums.ErrorType.DELETE_ERROR;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : delete specified task.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " 3";
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public Task deleteTask(TaskList taskList, int taskNum) throws JunoException {
        Task deletedTask;
        if (taskList.getTaskList().containsKey(taskNum)) {
            deletedTask = taskList.getTaskList().remove(taskNum);
            this.renumberTasks(taskList);
        } else {
            throw new JunoException(DELETE_ERROR, true, taskNum);
        }

        return deletedTask;
    }

    public void renumberTasks(TaskList taskList) {
        Map<Integer, Task> newTaskList = new LinkedHashMap<>();
        int newTaskCount = 0;

        for (Map.Entry<Integer, Task> entry : taskList.getTaskList().entrySet()) {
            newTaskList.put(++newTaskCount, entry.getValue());
        }

        taskList.setTaskList(newTaskList);
        taskList.updateTaskCount();
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException {
        Task deletedTask = this.deleteTask(taskList, this.taskNum);
        storage.saveTasks(taskList);
        ui.showDelete(deletedTask, taskList.getTaskCount());
    }
}
