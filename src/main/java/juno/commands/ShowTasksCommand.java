package juno.commands;

import juno.storage.Storage;
import juno.task.TaskList;
import juno.ui.Ui;

public class ShowTasksCommand extends Command {
    public static final String COMMAND_WORD = "show tasks";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : shows list of added tasks.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTasks(taskList);
    }
}
