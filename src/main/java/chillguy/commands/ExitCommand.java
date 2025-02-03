package chillguy.commands;

import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.Ui;

/**
 * Represents a command to exit the chatbot.
 * <p>
 * The {@code ExitCommand} class is responsible for handling the user request to exit the chatbot. When this command
 * is executed, it triggers the appropriate exit message to be displayed through the {@link Ui}.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : exits the chatbot.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD;

    /**
     * Checks if the provided command is an instance of {@link ExitCommand}.
     *
     * @param command the command to be checked.
     * @return {@code true} if the command is an {@code ExitCommand}, otherwise {@code false}.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    /**
     * Executes the exit command by displaying the exit message through the {@link Ui}.
     *
     * @param taskList the current task list (unused in this command).
     * @param storage the storage system (unused in this command).
     * @param ui the user interface to display the exit message.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showExitMessage();
    }
}
