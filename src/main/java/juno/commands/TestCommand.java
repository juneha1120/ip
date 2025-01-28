package juno.commands;

import juno.storage.Storage;
import juno.task.TaskList;
import juno.ui.Ui;

public class TestCommand extends Command {
    public static final String COMMAND_WORD = "#";
    public static final String COMMAND_LINE = "##########################################################";
    public static final String EMPTY_LINE = "";

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
    }
}
