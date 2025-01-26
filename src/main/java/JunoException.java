import enums.JunoErrorType;

public class JunoException extends Exception {
    public JunoException(JunoErrorType type) {
        super(getErrorMessage(type));
    }

    public JunoException(JunoErrorType type, boolean containsDesc) {
        super(getErrorMessage(type, containsDesc));
    }

    public JunoException(JunoErrorType type, boolean isDone, String task) {
        super(getErrorMessage(type, isDone, task));
    }

    public JunoException(JunoErrorType type, String line) {
        super(getErrorMessage(type, line));
    }

    public static String getErrorMessage(JunoErrorType type) {
        return switch (type) {
            case TODO_ERROR -> " Could you provide the details for your todo?";
            case LIST_ERROR -> " Looks like you have no tasks in your list now!";
            default -> "";
        };
    }

    public static String getErrorMessage(JunoErrorType type, boolean containsDesc) {
        return switch (type) {
            case DEADLINE_ERROR -> !containsDesc ? " Could you provide the details for your deadline?"
                    : " Please follow this format : \n deadline <description> /by <date/time>.";
            case EVENT_ERROR -> !containsDesc ? " Could you provide the details for your event?"
                    : " Please follow this format : \n event <description> /from <start> /to <end>.";
            default -> "";
        };
    }

    public static String getErrorMessage(JunoErrorType type, boolean isDone, String task) {
        return switch (type) {
            case MARK_ERROR -> isDone ? " The task is already marked as done!"
                    : " I don't seem to find the task : " + task;
            case UNMARK_ERROR -> !isDone ? " The task is not marked yet!"
                    : " I don't seem to find the task : " + task;
            default -> "";
        };
    }

    public static String getErrorMessage(JunoErrorType type, String line) {
        return switch (type) {
            case DELETE_ERROR -> " I don't seem to find this task : " + line;
            case LOAD_ERROR -> " I couldn't understand this line : " + line;
            case INVALID_FORMAT_ERROR -> "  Oops! the format is invalid for this line : " + line +
                    "\n  I will skip it for now and move on!";
            case INVALID_TYPE_ERROR -> "  I don't seem to understand the task type here : " + line +
                    "\n  I will skip it for now and move on!";
            default -> "";
        };
    }
}