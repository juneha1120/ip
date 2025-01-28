package juno.parser;

import static juno.enums.ErrorType.DATE_ERROR;
import static juno.enums.ErrorType.DEADLINE_ERROR;
import static juno.enums.ErrorType.DELETE_ERROR;
import static juno.enums.ErrorType.EVENT_ERROR;
import static juno.enums.ErrorType.MARK_ERROR;
import static juno.enums.ErrorType.TASK_ERROR;
import static juno.enums.ErrorType.TODO_ERROR;
import static juno.enums.ErrorType.UNMARK_ERROR;
import static juno.enums.TaskType.DEADLINE;
import static juno.enums.TaskType.EVENT;
import static juno.enums.TaskType.TODO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import juno.commands.AddCommand;
import juno.commands.Command;
import juno.commands.DeleteCommand;
import juno.commands.ExitCommand;
import juno.commands.HelpCommand;
import juno.commands.MarkCommand;
import juno.commands.ShowTasksCommand;
import juno.commands.ShowTasksWithDateCommand;
import juno.commands.TestCommand;
import juno.commands.TryAgainCommand;
import juno.commands.UnmarkCommand;
import juno.enums.TaskType;
import juno.exceptions.JunoException;
import juno.exceptions.JunoTestException;
import juno.task.Deadline;
import juno.task.Event;
import juno.task.Todo;

public class Parser {
    public Command parse(String fullCommand) throws JunoException {
        String[] command = fullCommand.split(" ", 2);
        if (command.length < 2) {
            command = new String[]{command[0], ""};
        }

        String commandWord = command[0];
        String arguments = command[1];
        if (commandWord.equals("show")) {
            if (arguments.contains("on")) {
                commandWord = ShowTasksWithDateCommand.COMMAND_WORD;
                arguments = arguments.split("on", 2)[1];
            } else {
                commandWord = ShowTasksCommand.COMMAND_WORD;
            }
        }

        return switch (commandWord) {
        case HelpCommand.COMMAND_WORD -> new HelpCommand();
        case Todo.COMMAND_WORD -> prepareAddCommand(TODO, arguments);
        case Deadline.COMMAND_WORD -> prepareAddCommand(DEADLINE, arguments);
        case Event.COMMAND_WORD -> prepareAddCommand(EVENT, arguments);
        case ShowTasksCommand.COMMAND_WORD -> new ShowTasksCommand();
        case ShowTasksWithDateCommand.COMMAND_WORD -> prepareShowWithDateCommand(arguments);
        case MarkCommand.COMMAND_WORD -> prepareMarkCommand(arguments);
        case UnmarkCommand.COMMAND_WORD -> prepareUnmarkCommand(arguments);
        case DeleteCommand.COMMAND_WORD -> prepareDeleteCommand(arguments);
        case ExitCommand.COMMAND_WORD -> new ExitCommand();
        case TestCommand.COMMAND_WORD, TestCommand.COMMAND_LINE, TestCommand.EMPTY_LINE ->
                throw new JunoTestException();
        default -> new TryAgainCommand(); };
    }

    protected boolean isTimeArgument(String argument) {
        return argument.trim().contains(" ");
    }

    protected Command prepareAddCommand(TaskType taskType, String arguments) throws JunoException {
        switch (taskType) {
        case TODO:
            if (arguments.isEmpty()) {
                throw new JunoException(TODO_ERROR);
            } else {
                return new AddCommand(new Todo(arguments));
            }
        case DEADLINE:
            if (arguments.isEmpty()) {
                throw new JunoException(DEADLINE_ERROR, false);
            } else if (!arguments.contains("/by")) {
                throw new JunoException(DEADLINE_ERROR, true);
            } else {
                String[] argumentSplit = arguments.split("/by", 2);
                String taskName = argumentSplit[0].trim();
                String taskBy = argumentSplit[1].trim();
                DateTimeFormatter byFormatter;
                if (this.isTimeArgument(taskBy)) {
                    byFormatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
                    try {
                        LocalDateTime by = LocalDateTime.parse(taskBy, byFormatter);
                        return new AddCommand(new Deadline(taskName, by));
                    } catch (DateTimeParseException e) {
                        throw new JunoException(DEADLINE_ERROR, true);
                    }
                } else {
                    byFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    try {
                        LocalDate by = LocalDate.parse(taskBy, byFormatter);
                        return new AddCommand(new Deadline(taskName, by));
                    } catch (DateTimeParseException e) {
                        throw new JunoException(DEADLINE_ERROR, true);
                    }
                }
            }
        case EVENT:
            if (arguments.isEmpty()) {
                throw new JunoException(EVENT_ERROR, false);
            } else if (!arguments.contains("/from") || !arguments.contains("/to")) {
                throw new JunoException(EVENT_ERROR, true);
            } else {
                String[] argumentSplit = arguments.split("/from", 2);
                String[] fromTo = argumentSplit[1].split("/to", 2);
                String taskName = argumentSplit[0].trim();
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
                            throw new JunoException(EVENT_ERROR, true);
                        }
                    } else {
                        toFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                        try {
                            LocalDateTime from = LocalDateTime.parse(taskFrom, fromFormatter);
                            LocalDate to = LocalDate.parse(taskTo, toFormatter);
                            return new AddCommand(new Event(taskName, from, to));
                        } catch (DateTimeParseException e) {
                            throw new JunoException(EVENT_ERROR, true);
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
                            throw new JunoException(EVENT_ERROR, true);
                        }
                    } else {
                        toFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                        try {
                            LocalDate from = LocalDate.parse(taskFrom, fromFormatter);
                            LocalDate to = LocalDate.parse(taskTo, toFormatter);
                            return new AddCommand(new Event(taskName, from, to));
                        } catch (DateTimeParseException e) {
                            throw new JunoException(EVENT_ERROR, true);
                        }
                    }
                }
            }
        default:
            throw new JunoException(TASK_ERROR);
        }
    }

    protected Command prepareShowWithDateCommand(String arguments) throws JunoException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate date = LocalDate.parse(arguments.trim(), inputFormatter);
            return new ShowTasksWithDateCommand(date);
        } catch (DateTimeParseException e) {
            throw new JunoException(DATE_ERROR);
        }
    }

    protected Command prepareMarkCommand(String arguments) throws JunoException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(arguments);
            return new MarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new JunoException(MARK_ERROR, arguments);
        }
    }

    protected Command prepareUnmarkCommand(String arguments) throws JunoException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(arguments);
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new JunoException(UNMARK_ERROR, arguments);
        }
    }

    protected Command prepareDeleteCommand(String arguments) throws JunoException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(arguments);
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new JunoException(DELETE_ERROR, arguments);
        }
    }
}
