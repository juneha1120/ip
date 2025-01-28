package juno.commands;

import juno.storage.Storage;
import juno.task.TaskList;
import juno.ui.Ui;

/**
 * Represents a command to display a "try again" message.
 * <p>
 * The {@code TryAgainCommand} class is responsible for prompting the user with a "try again" message when
 * the command is executed. It triggers the {@link Ui} to display a relevant message to the user.
 */
public class TryAgainCommand extends Command {
    /**
     * Executes the try again command by displaying a "try again" message through the {@link Ui}.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param ui the user interface to display the "try again" message.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTryAgainMessage();
    }
}
