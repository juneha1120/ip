package juno.command;

import juno.task.TaskList;

import juno.storage.Storage;

import juno.ui.Ui;

public class TryAgainCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTryAgainMessage();
    }
}
