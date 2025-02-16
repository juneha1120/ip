package chillguy.commands;

import chillguy.task.Deadline;
import chillguy.task.Event;
import chillguy.task.Todo;

/**
 * A utility class that contains various commands in ChillGuy.
 * <p>
 * The {@code CommandList} class serves as a centralized location for the available commands.
 * It provides an array of commands that can be used in the help system.
 */
public class CommandList {
    public static final String LINE_PREFIX = " # ";

    public static final String[] COMMAND_LIST = {
        LINE_PREFIX + Todo.COMMAND_WORD,
        LINE_PREFIX + Deadline.COMMAND_WORD,
        LINE_PREFIX + Event.COMMAND_WORD,
        LINE_PREFIX + ShowTasksCommand.COMMAND_PHRASE,
        LINE_PREFIX + ShowTasksWithDateCommand.COMMAND_PHRASE,
        LINE_PREFIX + FindCommand.COMMAND_WORD,
        LINE_PREFIX + RemindCommand.COMMAND_WORD,
        LINE_PREFIX + MarkCommand.COMMAND_WORD,
        LINE_PREFIX + UnmarkCommand.COMMAND_WORD,
        LINE_PREFIX + DeleteCommand.COMMAND_WORD,
        LINE_PREFIX + ExitCommand.COMMAND_WORD
    };
}
