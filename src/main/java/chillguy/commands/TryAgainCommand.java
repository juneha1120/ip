package chillguy.commands;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to display a "try again" message.
 * <p>
 * The {@code TryAgainCommand} class is responsible for prompting the user with a "try again" message when
 * the command is executed. It triggers the {@link TextUi} to display a relevant message to the user.
 */
public class TryAgainCommand extends Command {
    /**
     * Executes the try again command by displaying a "try again" message through the {@link TextUi}.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param textUi the user interface to display the "try again" message.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert textUi != null : "Text UI cannot be null";

        textUi.showTryAgainMessage();
    }

    /**
     * Executes the try again command by displaying a "try again" message through the {@link GraphicalUi}.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param graphicalUi the user interface to display the "try again" message.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert graphicalUi != null : "Graphical UI cannot be null";

        graphicalUi.appendTryAgainMessage();
    }
}
