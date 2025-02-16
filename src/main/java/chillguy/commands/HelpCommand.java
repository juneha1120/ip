package chillguy.commands;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to show a list of available commands in the chatbot.
 * <p>
 * The {@code HelpCommand} class is responsible for displaying a list of all available commands to the user when
 * the user requests help. It triggers the {@link TextUi} to show the help message containing all available commands.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : shows how to use specified command.\n"
            + EXAMPLE_PREFIX + COMMAND_WORD + "todo";
    private String commandDescription;

    /**
     * Constructs a {@code HelpCommand} with the specified command description.
     */
    public HelpCommand(String commandDescription) {
        this.commandDescription = commandDescription;
    }

    /**
     * Executes the help command by displaying the list of available commands through the {@link TextUi}.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param textUi the user interface to display the help message.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert textUi != null : "Text UI cannot be null";

        if (this.commandDescription.isEmpty()) {
            textUi.showCommandList();
        } else {
            textUi.showHelp(this.commandDescription);
        }
    }

    /**
     * Executes the help command by displaying the list of available commands through the {@link GraphicalUi}.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param graphicalUi the user interface to return the help message.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert graphicalUi != null : "Graphical UI cannot be null";

        if (this.commandDescription.isEmpty()) {
            graphicalUi.respondWithCommandList();
        } else {
            graphicalUi.respondWithHelpMessage(this.commandDescription);
        }
    }
}
