import enums.JunoType;

public class JunoException extends Exception {
    public JunoException(JunoType type, boolean containsDesc, boolean isDone) {
        super(getErrorMessage(type, containsDesc, isDone));
    }

    public static String getErrorMessage(JunoType type, boolean containsDesc, boolean isDone) {
        return switch (type) {
            case TODO -> " Could you provide the details for your todo?";
            case DEADLINE ->
                    !containsDesc ? " Could you provide the details for your deadline?"
                            : " Please follow this format : \n deadline <description> /by <date/time>.";
            case EVENT ->
                    !containsDesc ? " Could you provide the details for your event?"
                            : " Please follow this format : \n event <description> /from <start> /to <end>.";
            case LIST -> " Looks like you have no tasks in your list now!";
            case MARK ->
                    isDone ? " The task is already marked as done!"
                            : " I don't seem to find the task.";
            case UNMARK ->
                    !isDone ? " The task is not marked yet!"
                            : " I don't seem to find the task.";
            case DELETE -> " I don't seem to find the task.";
        };
    }
}