package command;

import task.TaskList;

import storage.Storage;

import ui.Ui;

public class TryAgainCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTryAgainMessage();
    }
}
