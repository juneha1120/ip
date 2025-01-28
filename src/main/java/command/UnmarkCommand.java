package command;

import exceptions.JunoException;

import task.Task;
import task.TaskList;

import storage.Storage;

import ui.Ui;

import static enums.ErrorType.UNMARK_ERROR;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : unmarks specified task.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " 3";
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException {
        Task curr = taskList.getTaskList().get(taskNum);
        if (curr == null) {
            throw new JunoException(UNMARK_ERROR, true, taskNum);
        } else if (!curr.isDone()) {
            throw new JunoException(UNMARK_ERROR, false, taskNum);
        }
        curr.unmark();
        storage.saveTasks(taskList);
        ui.showUnmark(curr);
    }
}