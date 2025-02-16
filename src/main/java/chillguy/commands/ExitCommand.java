package chillguy.commands;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents a command to exit the chatbot.
 * <p>
 * The {@code ExitCommand} class is responsible for handling the user request to exit the chatbot. When this command
 * is executed, it triggers the appropriate exit message to be displayed through the {@link TextUi}.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : exits the chatbot.\n"
            + EXAMPLE_PREFIX + COMMAND_WORD;

    /**
     * Checks if the provided command is an instance of {@link ExitCommand}.
     *
     * @param command the command to be checked.
     * @return {@code true} if the command is an {@code ExitCommand}, otherwise {@code false}.
     */
    public static boolean isExit(Command command) {
        assert command != null : "Command cannot be null";
        return command instanceof ExitCommand;
    }

    /**
     * Executes the exit command by displaying the exit message through the {@link TextUi}.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param textUi the user interface to display the exit message.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert textUi != null : "Text UI cannot be null";

        textUi.showExitMessage();
    }

    /**
     * Executes the exit command by returning the exit message through the {@link GraphicalUi}.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param graphicalUi the user interface to return the exit message.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert graphicalUi != null : "Graphical UI cannot be null";

        graphicalUi.respondWithExitMessage();
    }
}
