package juno.parser;

import juno.commands.*;

import juno.exceptions.JunoException;

import juno.exceptions.JunoTestException;
import juno.task.Todo;
import juno.task.Deadline;
import juno.task.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import juno.enums.TaskType;

import static juno.enums.ErrorType.*;
import static juno.enums.TaskType.*;

public class Parser {
    public Command parse(String fullCommand) throws JunoException {
        String[] command = fullCommand.split(" ", 2);
        if (command.length < 2) {
            command = new String[] {command[0], ""};
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

        switch (commandWord) {
            case HelpCommand.COMMAND_WORD :
                return new HelpCommand();
            case Todo.COMMAND_WORD :
                return prepareAddCommand(TODO, arguments);
            case Deadline.COMMAND_WORD :
                return prepareAddCommand(DEADLINE, arguments);
            case Event.COMMAND_WORD :
                return prepareAddCommand(EVENT, arguments);
            case ShowTasksCommand.COMMAND_WORD :
                return new ShowTasksCommand();
            case ShowTasksWithDateCommand.COMMAND_WORD :
                return prepareShowWithDateCommand(arguments);
            case MarkCommand.COMMAND_WORD :
                return prepareMarkCommand(arguments);
            case UnmarkCommand.COMMAND_WORD :
                return prepareUnmarkCommand(arguments);
            case DeleteCommand.COMMAND_WORD :
                return prepareDeleteCommand(arguments);
            case ExitCommand.COMMAND_WORD :
                return new ExitCommand();
            case TestCommand.COMMAND_WORD, TestCommand.COMMAND_LINE, TestCommand.EMPTY_LINE:
                throw new JunoTestException();
            default:
                return new TryAgainCommand();
        }
    }

    private boolean isTimeArgument(String argument) {
        return argument.trim().contains(" ");
    }

    private Command prepareAddCommand(TaskType taskType, String arguments) throws JunoException {
        switch (taskType) {
            case TODO :
                if (arguments.isEmpty()) {
                    throw new JunoException(TODO_ERROR);
                } else {
                    return new AddCommand(new Todo(arguments));
                }
            case DEADLINE :
                if (arguments.isEmpty()) {
                    throw new JunoException(DEADLINE_ERROR, false);
                } else if (!arguments.contains("/by")) {
                    throw new JunoException(DEADLINE_ERROR, true);
                } else {
                    String[] argumentSplitted = arguments.split("/by", 2);
                    String taskName = argumentSplitted[0].trim();
                    String taskBy = argumentSplitted[1].trim();
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
            case EVENT :
                if (arguments.isEmpty()) {
                    throw new JunoException(EVENT_ERROR, false);
                } else if (!arguments.contains("/from") || !arguments.contains("/to") ) {
                    throw new JunoException(EVENT_ERROR, true);
                } else {
                    String[] argumentSplitted = arguments.split("/from", 2);
                    String[] fromTo = argumentSplitted[1].split("/to", 2);
                    String taskName = argumentSplitted[0].trim();
                    String taskFrom = fromTo[0].trim();
                    String taskTo = fromTo[1].trim();
                    DateTimeFormatter fromFormatter, toFormatter;
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
            default :
                throw new JunoException(TASK_ERROR);
        }
    }

    private Command prepareShowWithDateCommand(String arguments) throws JunoException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            LocalDate date = LocalDate.parse(arguments.trim(), inputFormatter);
            return new ShowTasksWithDateCommand(date);
        } catch (DateTimeParseException e) {
            throw new JunoException(DATE_ERROR);
        }
    }

    private Command prepareMarkCommand(String arguments) throws JunoException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(arguments);
            return new MarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new JunoException(MARK_ERROR, arguments);
        }
    }

    private Command prepareUnmarkCommand(String arguments) throws JunoException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(arguments);
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new JunoException(UNMARK_ERROR, arguments);
        }
    }

    private Command prepareDeleteCommand(String arguments) throws JunoException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(arguments);
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new JunoException(DELETE_ERROR, arguments);
        }
    }
}