package chillguy.parser;

import static chillguy.enums.ErrorType.DATE_ERROR;
import static chillguy.enums.ErrorType.DEADLINE_ERROR;
import static chillguy.enums.ErrorType.DELETE_ERROR;
import static chillguy.enums.ErrorType.EVENT_ERROR;
import static chillguy.enums.ErrorType.MARK_ERROR;
import static chillguy.enums.ErrorType.TASK_ERROR;
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

        // Handle command words with more than two words
        if (commandWord.equals("show")) {
            if (arguments.contains("tasks on")) {
                commandWord = ShowTasksWithDateCommand.COMMAND_WORD;
                arguments = arguments.split("on", 2)[1];
            } else if (arguments.contains("tasks")) {
                commandWord = ShowTasksCommand.COMMAND_WORD;
            } else {
                commandWord = "tryagain";
            }
        } else if (commandWord.equals("chill") && arguments.equals("guy")) {
            commandWord = HelpCommand.COMMAND_WORD;
        }

        // Return corresponding Command based on the command word
        return switch (commandWord) {
        case HelpCommand.COMMAND_WORD -> new HelpCommand();
        case Todo.COMMAND_WORD -> prepareAddCommand(TODO, arguments);
        case Deadline.COMMAND_WORD -> prepareAddCommand(DEADLINE, arguments);
        case Event.COMMAND_WORD -> prepareAddCommand(EVENT, arguments);
        case ShowTasksCommand.COMMAND_WORD -> new ShowTasksCommand();
        case ShowTasksWithDateCommand.COMMAND_WORD -> prepareShowWithDateCommand(arguments);
        case FindCommand.COMMAND_WORD -> new FindCommand(arguments);
        case RemindCommand.COMMAND_WORD -> prepareRemindCommand(arguments);
        case MarkCommand.COMMAND_WORD -> prepareMarkCommand(arguments);
        case UnmarkCommand.COMMAND_WORD -> prepareUnmarkCommand(arguments);
        case DeleteCommand.COMMAND_WORD -> prepareDeleteCommand(arguments);
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        case TestCommand.COMMAND_WORD, TestCommand.COMMAND_LINE, TestCommand.EMPTY_LINE ->
                throw new ChillGuyTestException(fullCommand);
        default -> new TryAgainCommand(); };
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
     * Prepares an {@link AddCommand} based on the task type and the provided arguments.
     * It creates the appropriate task type ({@link Todo}, {@link Deadline}, or {@link Event})
     * and constructs an {@link AddCommand} to be executed.
     *
     * @param taskType The type of task to add (e.g., TODO, DEADLINE, or EVENT).
     * @param arguments The arguments associated with the task (e.g., description, date/time).
     * @return The {@link AddCommand} to be executed.
     * @throws ChillGuyException If there is an error in parsing the arguments or creating the task.
     */
    protected Command prepareAddCommand(TaskType taskType, String arguments) throws ChillGuyException {
        assert taskType != null : "Task type cannot be null";
        assert arguments != null : "Arguments cannot be null";

        switch (taskType) {
        case TODO:
            if (arguments.isEmpty()) {
                throw new ChillGuyException(TODO_ERROR);
            } else {
                return new AddCommand(new Todo(arguments));
            }
        case DEADLINE:
            if (arguments.isEmpty()) {
                throw new ChillGuyException(DEADLINE_ERROR, false);
            } else if (!arguments.contains("/by")) {
                throw new ChillGuyException(DEADLINE_ERROR, true);
            } else {
                String[] argumentSplit = arguments.split("/by", 2);
                String taskName = argumentSplit[0].trim();
                if (taskName.isEmpty()) {
                    throw new ChillGuyException(DEADLINE_ERROR, false);
                }
                String taskBy = argumentSplit[1].trim();
                DateTimeFormatter byFormatter;
                if (this.isTimeArgument(taskBy)) {
                    byFormatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
                    try {
                        LocalDateTime by = LocalDateTime.parse(taskBy, byFormatter);
                        return new AddCommand(new Deadline(taskName, by));
                    } catch (DateTimeParseException e) {
                        throw new ChillGuyException(DEADLINE_ERROR, true);
                    }
                } else {
                    byFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    try {
                        LocalDate by = LocalDate.parse(taskBy, byFormatter);
                        return new AddCommand(new Deadline(taskName, by));
                    } catch (DateTimeParseException e) {
                        throw new ChillGuyException(DEADLINE_ERROR, true);
                    }
                }
            }
        case EVENT:
            if (arguments.isEmpty()) {
                throw new ChillGuyException(EVENT_ERROR, false);
            } else if (!arguments.contains("/from") || !arguments.contains("/to")) {
                throw new ChillGuyException(EVENT_ERROR, true);
            } else {
                String[] argumentSplit = arguments.split("/from", 2);
                String[] fromTo = argumentSplit[1].split("/to", 2);
                String taskName = argumentSplit[0].trim();
                if (taskName.isEmpty()) {
                    throw new ChillGuyException(EVENT_ERROR, false);
                }
                String taskFrom = fromTo[0].trim();
                String taskTo = fromTo[1].trim();
                DateTimeFormatter fromFormatter;
                DateTimeFormatter toFormatter;
                if (this.isTimeArgument(taskFrom)) {
                    fromFormatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
                    if (this.isTimeArgument(taskTo)) {
                        toFormatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
                        try {
                            LocalDateTime from = LocalDateTime.parse(taskFrom, fromFormatter);
                            LocalDateTime to = LocalDateTime.parse(taskTo, toFormatter);
                            return new AddCommand(new Event(taskName, from, to));
                        } catch (DateTimeParseException e) {
                            throw new ChillGuyException(EVENT_ERROR, true);
                        }
                    } else {
                        toFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                        try {
                            LocalDateTime from = LocalDateTime.parse(taskFrom, fromFormatter);
                            LocalDate to = LocalDate.parse(taskTo, toFormatter);
                            return new AddCommand(new Event(taskName, from, to));
                        } catch (DateTimeParseException e) {
                            throw new ChillGuyException(EVENT_ERROR, true);
                        }
                    }
                } else {
                    fromFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    if (this.isTimeArgument(taskTo)) {
                        toFormatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
                        try {
                            LocalDate from = LocalDate.parse(taskFrom, fromFormatter);
                            LocalDateTime to = LocalDateTime.parse(taskTo, toFormatter);
                            return new AddCommand(new Event(taskName, from, to));
                        } catch (DateTimeParseException e) {
                            throw new ChillGuyException(EVENT_ERROR, true);
                        }
                    } else {
                        toFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                        try {
                            LocalDate from = LocalDate.parse(taskFrom, fromFormatter);
                            LocalDate to = LocalDate.parse(taskTo, toFormatter);
                            return new AddCommand(new Event(taskName, from, to));
                        } catch (DateTimeParseException e) {
                            throw new ChillGuyException(EVENT_ERROR, true);
                        }
                    }
                }
            }
        default:
            throw new ChillGuyException(TASK_ERROR);
        }
    }

    /**
     * Prepares a {@link ShowTasksWithDateCommand} with a date argument parsed from the user input.
     *
     * @param arguments The arguments provided by the user, expected to be a date string in the format "d/M/yyyy".
     * @return A {@link ShowTasksWithDateCommand} object for displaying tasks on the specified date.
     * @throws ChillGuyException If the date format is invalid.
     */
    protected Command prepareShowWithDateCommand(String arguments) throws ChillGuyException {
        assert arguments != null : "Arguments cannot be null";

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate date = LocalDate.parse(arguments.trim(), inputFormatter);
            return new ShowTasksWithDateCommand(date);
        } catch (DateTimeParseException e) {
            throw new ChillGuyException(DATE_ERROR);
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

        if (arguments.equals(Todo.COMMAND_WORD)) {
            return new RemindCommand(TODO);
        } else if (arguments.equals(Deadline.COMMAND_WORD)) {
            return new RemindCommand(DEADLINE);
        } else if (arguments.equals(Event.COMMAND_WORD)) {
            return new RemindCommand(EVENT);
        } else {
            throw new ChillGuyException(TYPE_ERROR);
        }
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
