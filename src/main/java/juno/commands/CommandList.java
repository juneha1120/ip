package juno.commands;

import juno.task.Todo;
import juno.task.Deadline;
import juno.task.Event;

public class CommandList {
    public static final String LINE_PREFIX = " # ";
    public static final String EMPTY_DIVIDER = "";
    public static final String[] commandDescriptionList = {
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
            LINE_PREFIX + MarkCommand.COMMAND_DESCRIPTION,
            EMPTY_DIVIDER,
            LINE_PREFIX + UnmarkCommand.COMMAND_DESCRIPTION,
            EMPTY_DIVIDER,
            LINE_PREFIX + DeleteCommand.COMMAND_DESCRIPTION,
            EMPTY_DIVIDER,
            LINE_PREFIX + ExitCommand.COMMAND_DESCRIPTION
    };
}
