package juno.commands;

import juno.storage.Storage;
import juno.task.TaskList;
import juno.ui.Ui;

/**
 * Represents a command to show a list of available commands in the chatbot.
 * <p>
 * The {@code HelpCommand} class is responsible for displaying a list of all available commands to the user when
 * the user requests help. It triggers the {@link Ui} to show the help message containing all available commands.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "juno";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : shows list of available commands.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD;

    /**
     * Executes the help command by displaying the list of available commands through the {@link Ui}.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param ui the user interface to display the help message.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showHelp();
    }
}
