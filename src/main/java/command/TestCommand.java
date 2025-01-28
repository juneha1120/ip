package command;

import task.TaskList;

import storage.Storage;

import ui.Ui;

public class TestCommand extends Command{
    public static final String COMMAND_WORD = "#";
    public static final String COMMAND_LINE = "##########################################################";
    public static final String EMPTY_LINE = "";

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        return;
    }
}
