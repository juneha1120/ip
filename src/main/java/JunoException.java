import enums.JunoType;

public class JunoException extends Exception {
    public JunoException(JunoType type, boolean containsDesc) {
        super(getErrorMessage(type, containsDesc));
    }

    public static String getErrorMessage(JunoType type, boolean containsDesc) {
        return switch (type) {
            case TODO -> " Could you provide the details for your todo?";
            case DEADLINE ->
                    !containsDesc ? " Could you provide the details for your deadline?"
                                 : " Please follow this format : \n deadline <description> /by <date/time>.";
            case EVENT ->
                    !containsDesc ? " Could you provide the details for your event?"
                                 : " Please follow this format : \n event <description> /from <start> /to <end>.";
            case LIST -> " Looks like you have no tasks in your list now!";
            case MARK, UNMARK, DELETE -> " I don't seem to find the task.";
        };
    }
}