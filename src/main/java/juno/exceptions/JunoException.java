package juno.exceptions;

import juno.enums.ErrorType;

public class JunoException extends Exception {
    public JunoException(ErrorType type) {
        super(getErrorMessage(type));
    }

    public JunoException(ErrorType type, boolean containsDesc) {
        super(getErrorMessage(type, containsDesc));
    }

    public JunoException(ErrorType type, boolean isDone, int taskNum) {
        super(getErrorMessage(type, isDone, taskNum));
    }

    public JunoException(ErrorType type, String line) {
        super(getErrorMessage(type, line));
    }

    public JunoException() {
    }

    public static String getErrorMessage(ErrorType type) {
        return switch (type) {
            case TODO_ERROR -> "Could you provide the details for your todo?";
            case LIST_ERROR -> "Looks like you have no tasks in your list now!";
            case TASK_ERROR -> "I don't seem to know about that task.";
            case DATE_ERROR -> "Please follow this format : show tasks on <d/M/yyyy>.";
            case CREATE_FILE_ERROR -> "I can't seem to create save files.";
            case READ_FILE_ERROR -> "I can't seem to read saved files.";
            case LIST_WITH_DATE_ERROR -> "Looks like you have no tasks on the date!";
            case LIST_WITH_KEYWORD_ERROR -> "Looks like you have no matching tasks.";
            case NO_KEYWORD_ERROR -> "Please specify the keyword you want to search.";
            default -> "";
        };
    }

    public static String getErrorMessage(ErrorType type, boolean containsDesc) {
        return switch (type) {
            case DEADLINE_ERROR -> !containsDesc ? "Could you provide the details for your deadline?"
                    : "Please follow this format : " +
                    "\n  deadline <description> /by <d/M/yyyy (HHmm)>.";
            case EVENT_ERROR -> !containsDesc ? "Could you provide the details for your event?"
                    : "Please follow this format : " +
                    "\n  event <description> /from <d/M/yyyy (HHmm)> /to <d/M/yyyy (HHmm)>.";
            default -> "";
        };
    }

    public static String getErrorMessage(ErrorType type, boolean isDone, int taskNum) {
        return switch (type) {
            case MARK_ERROR -> isDone ? "The task is already marked as done!"
                    : "I don't seem to find the task : " + taskNum;
            case UNMARK_ERROR -> !isDone ? "The task is not marked yet!"
                    : "I don't seem to find the task : " + taskNum;
            case DELETE_ERROR -> "I don't seem to find the task : " + taskNum;
            default -> "";
        };
    }

    public static String getErrorMessage(ErrorType type, String line) {
        return switch (type) {
            case DELETE_ERROR -> "I don't seem to find this task : " + line;
            case LOAD_ERROR -> "I couldn't understand this line : " + line;
            case INVALID_FORMAT_ERROR -> "Oops! the format is invalid for this line : " + line +
                    "\n I will skip it for now and move on!";
            case INVALID_TYPE_ERROR -> "I don't seem to understand the task type here : " + line +
                    "\n I will skip it for now and move on!";
            case MARK_ERROR, UNMARK_ERROR -> "I don't seem to find the task : " + line;
            default -> "";
        };
    }
}
