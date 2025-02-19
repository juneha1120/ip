package chillguy.exceptions;

import chillguy.enums.ErrorType;

/**
 * Represents a custom exception class for the ChillGuy chatbot.
 * <p>
 * The {@code ChillGuyException} class extends {@link Exception} and provides specific error messages based on the type
 * of error encountered in the chatbot's operation. These error messages are determined by the {@link ErrorType}
 * provided when throwing the exception.
 */
public class ChillGuyException extends Exception {
    /**
     * Constructs a {@code ChillGuyException} with the specified error type.
     *
     * @param type the error type that defines the message for the exception.
     */
    public ChillGuyException(ErrorType type) {
        super(getErrorMessage(type));
    }

    /**
     * Constructs a {@code ChillGuyException} with the specified error type and a flag for including name.
     *
     * @param type the error type that defines the message for the exception.
     * @param containsName a flag indicating whether the name should be included in the message.
     */
    public ChillGuyException(ErrorType type, boolean containsName) {
        super(getErrorMessage(type, containsName));
    }

    /**
     * Constructs a {@code ChillGuyException} with the specified error type, task completion status, and task number.
     *
     * @param type the error type that defines the message for the exception.
     * @param isDone the completion status of the task.
     * @param taskNum the task number related to the error.
     */
    public ChillGuyException(ErrorType type, boolean isDone, int taskNum) {
        super(getErrorMessage(type, isDone, taskNum));
    }

    /**
     * Constructs a {@code ChillGuyException} with the specified error type and line of input.
     *
     * @param type the error type that defines the message for the exception.
     * @param line the line of input that caused the error.
     */
    public ChillGuyException(ErrorType type, String line) {
        super(getErrorMessage(type, line));
    }

    /**
     * Retrieves the error message for the given error type.
     *
     * @param type the error type to retrieve the corresponding error message.
     * @return the error message associated with the specified error type.
     */
    public static String getErrorMessage(ErrorType type) {
        return switch (type) {
        case TODO_ERROR -> """
                We chill, but could you provide the name
                for your todo?""";
        case DATE_ERROR -> """
                We chill, but could follow this format?
                show tasks on <d/M/yyyy>.""";
        case CREATE_FILE_ERROR -> """
                We chill, but I can't seem to create save files.""";
        case READ_FILE_ERROR -> """
                We chill, but I can't seem to read saved files.""";
        case LIST_WITH_DATE_ERROR -> """
                We chill, but looks like you have
                no tasks on the date.""";
        case LIST_WITH_KEYWORD_ERROR -> """
                We chill, but looks like you have
                no matching ones.""";
        case NO_KEYWORD_ERROR -> """
                We chill, but could you tell me
                the keyword to search?""";
        case TYPE_ERROR -> """
                We chill, but could you tell me
                the valid task type to get reminders for?""";
        case REMINDERS_ERROR -> """
                We chill, but looks like you have
                no reminders for the task type today.""";
        default -> "";
        };
    }

    /**
     * Retrieves the error message for the given error type and name flag.
     *
     * @param type the error type to retrieve the corresponding error message.
     * @param containsName flag to indicate whether name is included in the message.
     * @return the error message associated with the specified error type and description flag.
     */
    public static String getErrorMessage(ErrorType type, boolean containsName) {
        return switch (type) {
        case DEADLINE_ERROR -> !containsName ? """
                We chill, but could you provide the name for your deadline?"""
                : """
                We chill, but could you follow this format?
                deadline <task name> /by <d/M/yyyy (HH:mm)>.""";
        case EVENT_ERROR -> !containsName ? """
                We chill, but could you provide the name for your event?"""
                : """
                We chill, but could you follow this format?
                event <task name> /from <d/M/yyyy (HH:mm)> /to <d/M/yyyy (HH:mm)>.""";
        default -> ""; };
    }

    /**
     * Retrieves the error message for the given error type, task completion status, and task number.
     *
     * @param type the error type to retrieve the corresponding error message.
     * @param isDone the completion status of the task.
     * @param taskNum the task number related to the error.
     * @return the error message associated with the specified error type, task status, and task number.
     */
    public static String getErrorMessage(ErrorType type, boolean isDone, int taskNum) {
        return switch (type) {
        case MARK_ERROR -> isDone ? "We chill, but the task is already marked as done."
                : "We chill, but I don't seem to find the task: " + taskNum + ".";
        case UNMARK_ERROR -> !isDone ? "We chill, but the task is not marked yet."
                : "We chill, but I don't seem to find the task: " + taskNum + ".";
        case DELETE_ERROR -> "We chill, but I don't seem to find the task: " + taskNum + ".";
        default -> ""; };
    }

    /**
     * Retrieves the error message for the given error type and input line.
     *
     * @param type the error type to retrieve the corresponding error message.
     * @param line the line of input that caused the error.
     * @return the error message associated with the specified error type and input line.
     */
    public static String getErrorMessage(ErrorType type, String line) {
        return switch (type) {
        case COMMAND_ERROR -> "We chill, but I don't have this command: " + line + ".";
        case DELETE_ERROR -> "We chill, but I don't seem to find this task: " + line + ".";
        case READ_FORMAT_ERROR -> "We chill, but the format is invalid for this line: " + line
                + "\n" + "I will skip it for now and move on...";
        case READ_TYPE_ERROR -> "We chill, but I don't seem to understand the type here: " + line
                + "\n" + "I will skip it for now and move on...";
        case MARK_ERROR, UNMARK_ERROR -> "We chill, but I don't seem to find the task: " + line + ".";
        default -> ""; };
    }
}
