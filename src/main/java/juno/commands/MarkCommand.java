package juno.commands;

import static juno.enums.ErrorType.MARK_ERROR;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.Task;
import juno.task.TaskList;
import juno.ui.Ui;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : marks specified task as done.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " 3";
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException {
        Task curr = taskList.getTaskList().get(taskNum);
        if (curr == null) {
            throw new JunoException(MARK_ERROR, false, taskNum);
        } else if (curr.isDone()) {
            throw new JunoException(MARK_ERROR, true, taskNum);
        }
        curr.mark();
        storage.saveTasks(taskList);
        ui.showMark(curr);
    }
}
