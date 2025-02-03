package chillguy.commands;

import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.Ui;

/**
 * Represents a test command that serves as a placeholder for test comments.
 * <p>
 * The {@code TestCommand} class does not perform any operation when executed. It is intended to be used as a
 * placeholder or for test comments. It contains no logic in the {@link #execute} method, effectively performing
 * no action.
 */
public class TestCommand extends Command {
    public static final String COMMAND_WORD = "#";
    public static final String COMMAND_LINE = "##########################################################";
    public static final String EMPTY_LINE = "";

    /**
     * Executes the test command. This method performs no operation as it serves as a placeholder for test comments.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param ui the user interface (unused in this command).
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
    }
}
