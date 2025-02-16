package chillguy.parser;

import static chillguy.enums.ErrorType.COMMAND_ERROR;
import static chillguy.enums.ErrorType.DATE_ERROR;
import static chillguy.enums.ErrorType.DEADLINE_ERROR;
import static chillguy.enums.ErrorType.DELETE_ERROR;
import static chillguy.enums.ErrorType.EVENT_ERROR;
import static chillguy.enums.ErrorType.MARK_ERROR;
import static chillguy.enums.ErrorType.TODO_ERROR;
import static chillguy.enums.ErrorType.TYPE_ERROR;
import static chillguy.enums.ErrorType.UNMARK_ERROR;
import static chillguy.enums.TaskType.DEADLINE;
import static chillguy.enums.TaskType.EVENT;
import static chillguy.enums.TaskType.TODO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.Temporal;

import chillguy.commands.AddCommand;
import chillguy.commands.Command;
import chillguy.commands.DeleteCommand;
import chillguy.commands.ExitCommand;
import chillguy.commands.FindCommand;
import chillguy.commands.HelpCommand;
import chillguy.commands.MarkCommand;
import chillguy.commands.RemindCommand;
import chillguy.commands.ShowTasksCommand;
import chillguy.commands.ShowTasksWithDateCommand;
import chillguy.commands.TestCommand;
import chillguy.commands.TryAgainCommand;
import chillguy.commands.UnmarkCommand;
import chillguy.enums.TaskType;
import chillguy.exceptions.ChillGuyException;
import chillguy.exceptions.ChillGuyTestException;
import chillguy.task.Deadline;
import chillguy.task.Event;
import chillguy.task.Todo;

/**
 * The {@code Parser} class is responsible for interpreting the user's input commands and converting them
 * into executable {@link Command} objects. It supports various task-related commands such as adding,
 * marking, and deleting tasks. It also handles parsing commands with date or time-related arguments.
 */
public class Parser {
    /**
     * Parses a full user command into a corresponding {@link Command} object.
     * It splits the command and its arguments, processes the command type,
     * and prepares the appropriate action.
     *
     * @param fullCommand The full user command entered.
     * @return A corresponding {@link Command} object to be executed.
     * @throws ChillGuyException If there is an error in parsing the command or its arguments.
     */
    public Command parse(String fullCommand) throws ChillGuyException, ChillGuyTestException {
        assert fullCommand != null : "Command cannot be null";

        String[] command = fullCommand.split(" ", 2);
        if (command.length < 2) {
            command = new String[]{command[0], ""};
        }

        String commandWord = command[0];
        String arguments = command[1];

        // Return corresponding Command based on the command word
        return switch (commandWord) {
        case HelpCommand.COMMAND_WORD -> prepareHelpCommand(arguments);
        case Todo.COMMAND_WORD -> prepareAddCommand(TODO, arguments);
        case Deadline.COMMAND_WORD -> prepareAddCommand(DEADLINE, arguments);
        case Event.COMMAND_WORD -> prepareAddCommand(EVENT, arguments);
        case ShowTasksCommand.COMMAND_WORD -> prepareShowTasksCommand(fullCommand);
        case FindCommand.COMMAND_WORD -> new FindCommand(arguments);
        case RemindCommand.COMMAND_WORD -> prepareRemindCommand(arguments);
        case MarkCommand.COMMAND_WORD -> prepareMarkCommand(arguments);
        case UnmarkCommand.COMMAND_WORD -> prepareUnmarkCommand(arguments);
        case DeleteCommand.COMMAND_WORD -> prepareDeleteCommand(arguments);
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        case TestCommand.COMMAND_WORD, TestCommand.COMMAND_LINE, TestCommand.EMPTY_LINE ->
                throw new ChillGuyTestException(fullCommand);
        default -> new TryAgainCommand();
        };
    }

    /**
     * Prepares a {@link HelpCommand} to show commands and their descriptions, based on the provided arguments.
     *
     * @param arguments The arguments provided by the user
     * @return A {@link HelpCommand} object for unmarking the task.
     * @throws ChillGuyException If the argument is invalid.
     */
    protected Command prepareHelpCommand(String arguments) throws ChillGuyException {
        assert arguments != null : "Arguments cannot be null";

        return switch (arguments) {
        case "" -> new HelpCommand("");
        case Todo.COMMAND_WORD -> new HelpCommand(Todo.COMMAND_DESCRIPTION);
        case Deadline.COMMAND_WORD -> new HelpCommand(Deadline.COMMAND_DESCRIPTION);
        case Event.COMMAND_WORD -> new HelpCommand(Event.COMMAND_DESCRIPTION);
        case ShowTasksCommand.COMMAND_PHRASE -> new HelpCommand(ShowTasksCommand.COMMAND_DESCRIPTION);
        case ShowTasksWithDateCommand.COMMAND_PHRASE -> new HelpCommand(ShowTasksWithDateCommand.COMMAND_DESCRIPTION);
        case FindCommand.COMMAND_WORD -> new HelpCommand(FindCommand.COMMAND_DESCRIPTION);
        case RemindCommand.COMMAND_WORD -> new HelpCommand(RemindCommand.COMMAND_DESCRIPTION);
        case MarkCommand.COMMAND_WORD -> new HelpCommand(MarkCommand.COMMAND_DESCRIPTION);
        case UnmarkCommand.COMMAND_WORD -> new HelpCommand(UnmarkCommand.COMMAND_DESCRIPTION);
        case DeleteCommand.COMMAND_WORD -> new HelpCommand(DeleteCommand.COMMAND_DESCRIPTION);
        case ExitCommand.COMMAND_WORD -> new HelpCommand(ExitCommand.COMMAND_DESCRIPTION);
        default -> throw new ChillGuyException(COMMAND_ERROR, arguments);
        };
    }

    /**
     * Checks if the given argument contains time information.
     * This is used to differentiate between date-only and date-time arguments.
     *
     * @param argument The argument to check.
     * @return {@code true} if the argument contains time information, {@code false} otherwise.
     */
    protected boolean isTimeArgument(String argument) {
        assert argument != null : "Argument cannot be null";
        return argument.trim().contains(" ");
    }

    /**
     * Parses a date or date-time string and converts it into a {@link Temporal} object.
     * Supports two formats: "d/M/yyyy" (date) and "d/M/yyyy H:mm" (date with time).
     *
     * @param dateTime The date/time string to parse.
     * @return A {@link LocalDate} if only a date is provided, or a {@link LocalDateTime} if a time is included.
     * @throws ChillGuyException If the date/time format is invalid.
     */
    private Temporal parseDateTime(String dateTime, boolean isDeadline) throws ChillGuyException {
        DateTimeFormatter formatter;
        if (this.isTimeArgument(dateTime)) {
            formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
            try {
                return LocalDateTime.parse(dateTime, formatter);
            } catch (DateTimeParseException e) {
                if (isDeadline) {
                    throw new ChillGuyException(DEADLINE_ERROR, true);
                } else {
                    throw new ChillGuyException(EVENT_ERROR, true);
                }
            }
        } else {
            formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            try {
                return LocalDate.parse(dateTime, formatter);
            } catch (DateTimeParseException e) {
                if (isDeadline) {
                    throw new ChillGuyException(DEADLINE_ERROR, true);
                } else {
                    throw new ChillGuyException(EVENT_ERROR, true);
                }
            }
        }
    }

    /**
     * Prepares an {@link AddCommand} based on the task type and the provided arguments.
     * It delegates the task creation to specific methods for {@link Todo}, {@link Deadline}, or {@link Event}.
     *
     * @param taskType  The type of task to add (e.g., TODO, DEADLINE, or EVENT).
     * @param arguments The arguments associated with the task (e.g., description, date/time).
     * @return The {@link AddCommand} to be executed.
     * @throws ChillGuyException If there is an error in parsing the arguments or creating the task.
     */
    protected Command prepareAddCommand(TaskType taskType, String arguments) throws ChillGuyException {
        assert taskType != null : "Task type cannot be null";
        assert arguments != null : "Arguments cannot be null";

        return switch (taskType) {
        case TODO -> prepareTodoCommand(arguments);
        case DEADLINE -> prepareDeadlineCommand(arguments);
        case EVENT -> prepareEventCommand(arguments);
        };
    }

    /**
     * Creates an {@link AddCommand} for a TODO task.
     *
     * @param arguments The description of the TODO task.
     * @return The {@link AddCommand} containing the TODO task.
     * @throws ChillGuyException If the arguments are empty.
     */
    private Command prepareTodoCommand(String arguments) throws ChillGuyException {
        if (arguments.isEmpty()) {
            throw new ChillGuyException(TODO_ERROR);
        }
        return new AddCommand(new Todo(arguments));
    }

    /**
     * Creates an {@link AddCommand} for a DEADLINE task.
     *
     * @param arguments The description and due date of the DEADLINE task in "task /by date" format.
     * @return The {@link AddCommand} containing the DEADLINE task.
     * @throws ChillGuyException If the arguments are invalid or incorrectly formatted.
     */
    private Command prepareDeadlineCommand(String arguments) throws ChillGuyException {
        if (arguments.isEmpty()) {
            throw new ChillGuyException(DEADLINE_ERROR, false);
        }
        if (!arguments.contains("/by")) {
            throw new ChillGuyException(DEADLINE_ERROR, true);
        }

        String[] argumentSplit = arguments.split("/by", 2);
        String taskName = argumentSplit[0].trim();
        String taskBy = argumentSplit[1].trim();

        if (taskName.isEmpty()) {
            throw new ChillGuyException(DEADLINE_ERROR, false);
        }

        boolean isDeadline = true;
        Temporal by = parseDateTime(taskBy, isDeadline);

        if (by instanceof LocalDateTime) {
            return new AddCommand(new Deadline(taskName, (LocalDateTime) by));
        } else {
            return new AddCommand(new Deadline(taskName, (LocalDate) by));
        }
    }

    /**
     * Creates an {@link AddCommand} for an EVENT task.
     *
     * @param arguments The description, start, and end date/time of EVENT task in "task /from date /to date" format.
     * @return The {@link AddCommand} containing the EVENT task.
     * @throws ChillGuyException If the arguments are invalid or incorrectly formatted.
     */
    private Command prepareEventCommand(String arguments) throws ChillGuyException {
        if (arguments.isEmpty()) {
            throw new ChillGuyException(EVENT_ERROR, false);
        }
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new ChillGuyException(EVENT_ERROR, true);
        }

        String[] argumentSplit = arguments.split("/from", 2);
        String[] fromTo = argumentSplit[1].split("/to", 2);
        String taskName = argumentSplit[0].trim();
        String taskFrom = fromTo[0].trim();
        String taskTo = fromTo[1].trim();

        if (taskName.isEmpty()) {
            throw new ChillGuyException(EVENT_ERROR, false);
        }

        boolean isDeadline = false;
        Temporal from = parseDateTime(taskFrom, isDeadline);
        Temporal to = parseDateTime(taskTo, isDeadline);

        if (from instanceof LocalDateTime && to instanceof LocalDateTime) {
            return new AddCommand(new Event(taskName, (LocalDateTime) from, (LocalDateTime) to));
        } else if (from instanceof LocalDateTime && to instanceof LocalDate) {
            return new AddCommand(new Event(taskName, (LocalDateTime) from, (LocalDate) to));
        } else if (from instanceof LocalDate && to instanceof LocalDateTime) {
            return new AddCommand(new Event(taskName, (LocalDate) from, (LocalDateTime) to));
        } else {
            assert from instanceof LocalDate;
            return new AddCommand(new Event(taskName, (LocalDate) from, (LocalDate) to));
        }
    }

    /**
     * Prepares either a {@link ShowTasksCommand}, {@link ShowTasksWithDateCommand},
     * or {@link TryAgainCommand}
     * depending on if the argument parsed from the user input includes relevant argument.
     *
     * @param fullCommand The full string command provided by the user.
     * @return A command object for displaying all tasks, displaying tasks on the specified date, or try-again.
     * @throws ChillGuyException If the date format is invalid.
     */
    protected Command prepareShowTasksCommand(String fullCommand) throws ChillGuyException {
        assert fullCommand != null : "Arguments cannot be null";

        if (fullCommand.contains(ShowTasksWithDateCommand.COMMAND_PHRASE)) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            try {
                String dateArgument = fullCommand.substring(fullCommand
                        .indexOf(ShowTasksWithDateCommand.COMMAND_PHRASE)
                        + ShowTasksWithDateCommand.COMMAND_PHRASE.length()).trim();
                LocalDate date = LocalDate.parse(dateArgument, inputFormatter);
                return new ShowTasksWithDateCommand(date);
            } catch (DateTimeParseException e) {
                throw new ChillGuyException(DATE_ERROR);
            }
        } else if (fullCommand.contains(ShowTasksCommand.COMMAND_PHRASE)) {
            return new ShowTasksCommand();
        } else {
            return new TryAgainCommand();
        }
    }

    /**
     * Prepares a {@link RemindCommand} with a type argument parsed from the user input.
     *
     * @param arguments The arguments provided by the user, expected to be a task type string.
     * @return A {@link RemindCommand} object for displaying reminders of specified task type.
     * @throws ChillGuyException If the task type string is invalid.
     */
    protected Command prepareRemindCommand(String arguments) throws ChillGuyException {
        assert arguments != null : "Arguments cannot be null";

        if (arguments.isEmpty()) {
            throw new ChillGuyException(TYPE_ERROR);
        }

        return switch (arguments) {
        case Todo.COMMAND_WORD -> new RemindCommand(TODO);
        case Deadline.COMMAND_WORD -> new RemindCommand(DEADLINE);
        case Event.COMMAND_WORD -> new RemindCommand(EVENT);
        default -> throw new ChillGuyException(TYPE_ERROR);
        };
    }

    /**
     * Prepares a {@link MarkCommand} to mark a task as done, based on the provided task number.
     *
     * @param arguments The arguments provided by the user, expected to be a valid task number.
     * @return A {@link MarkCommand} object for marking the task as done.
     * @throws ChillGuyException If the task number is invalid or not a valid integer.
     */
    protected Command prepareMarkCommand(String arguments) throws ChillGuyException {
        assert arguments != null : "Arguments cannot be null";

        int taskNum;
        try {
            taskNum = Integer.parseInt(arguments);
            return new MarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ChillGuyException(MARK_ERROR, arguments);
        }
    }

    /**
     * Prepares an {@link UnmarkCommand} to unmark a task as not done, based on the provided task number.
     *
     * @param arguments The arguments provided by the user, expected to be a valid task number.
     * @return An {@link UnmarkCommand} object for unmarking the task.
     * @throws ChillGuyException If the task number is invalid or not a valid integer.
     */
    protected Command prepareUnmarkCommand(String arguments) throws ChillGuyException {
        assert arguments != null : "Arguments cannot be null";

        int taskNum;
        try {
            taskNum = Integer.parseInt(arguments);
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ChillGuyException(UNMARK_ERROR, arguments);
        }
    }

    /**
     * Prepares a {@link DeleteCommand} to delete a task, based on the provided task number.
     *
     * @param arguments The arguments provided by the user, expected to be a valid task number.
     * @return A {@link DeleteCommand} object for deleting the task.
     * @throws ChillGuyException If the task number is invalid or not a valid integer.
     */
    protected Command prepareDeleteCommand(String arguments) throws ChillGuyException {
        assert arguments != null : "Arguments cannot be null";

        int taskNum;
        try {
            taskNum = Integer.parseInt(arguments);
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ChillGuyException(DELETE_ERROR, arguments);
        }
    }
}
