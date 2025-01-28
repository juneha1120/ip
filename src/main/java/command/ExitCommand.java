package command;

import task.TaskList;

import storage.Storage;

import ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : exits the chatbot.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD;

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showExitMessage();
    }
}
