package juno.commands;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.TaskList;
import juno.ui.Ui;

/**
 * Represents an abstract command that can be executed within Juno.
 * <p>
 * The {@code Command} class serves as a blueprint for specific commands that interact with a {@link TaskList},
 * {@link Storage}, and {@link Ui}.
 */
public abstract class Command {
    public static final String EXAMPLE_PREFIX = " ".repeat(7) + "For example, ";

    /**
     * Executes the specific command, interacting with the given {@link TaskList}, {@link Storage}, and {@link Ui}.
     *
     * @param taskList the list of tasks to be modified by the command.
     * @param storage the storage system to save data during command execution.
     * @param ui the user interface to display relevant information to the user.
     * @throws JunoException if an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException;
}
