package chillguy.commands;

import chillguy.task.Deadline;
import chillguy.task.Event;
import chillguy.task.Todo;

/**
 * A utility class that contains descriptions for various commands in  ChillGuy.
 * <p>
 * The {@code CommandList} class serves as a centralized location for the descriptions of available commands.
 * It provides an array of command descriptions that can be used in the help system.
 */
public class CommandList {
    public static final String LINE_PREFIX = " # ";
    public static final String EMPTY_DIVIDER = "";

    public static final String[] COMMAND_DESCRIPTION_LIST = {
        LINE_PREFIX + HelpCommand.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + Todo.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + Deadline.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + Event.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + ShowTasksCommand.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + ShowTasksWithDateCommand.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + FindCommand.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + MarkCommand.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + UnmarkCommand.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + DeleteCommand.COMMAND_DESCRIPTION,
        EMPTY_DIVIDER,
        LINE_PREFIX + ExitCommand.COMMAND_DESCRIPTION
    };
}
