package chillguy.commands;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * Represents an abstract command that can be executed within ChillGuy.
 * <p>
 * The {@code Command} class serves as a blueprint for specific commands that interact with a {@link TaskList},
 * {@link Storage}, and {@link TextUi}.
 */
public abstract class Command {
    public static final String EXAMPLE_PREFIX = " ".repeat(7) + "For example, ";

    /**
     * Executes the specific command, interacting with the given {@link TaskList}, {@link Storage}, and {@link TextUi}.
     *
     * @param taskList the list of tasks to be modified by the command.
     * @param storage the storage system to save data during command execution.
     * @param textUi the user interface to display relevant information to the user.
     * @throws ChillGuyException if an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList taskList, Storage storage, TextUi textUi) throws ChillGuyException;

    /**
     * Executes the specific command, interacting with the given {@link TaskList}, {@link Storage}, and {@link GraphicalUi}.
     *
     * @param taskList the list of tasks to be modified by the command.
     * @param storage the storage system to save data during command execution.
     * @param graphicalUi Ui the user interface to return relevant information to the user.
     * @throws ChillGuyException if an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException;
}
