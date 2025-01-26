import static enums.JunoErrorType.INVALID_FORMAT_ERROR;
import static enums.JunoErrorType.INVALID_TYPE_ERROR;

public abstract class JunoTask {
    protected String description;
    protected boolean isDone;

    public JunoTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    public JunoTask(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public abstract String toFileFormat();

    public static JunoTask fromFileFormat(String line) throws JunoException {
        String[] parts = line.split(" \\| ");
        if (line.isEmpty()) {
            return null;
        } else if (parts.length < 3) {
            throw new JunoException(INVALID_FORMAT_ERROR, line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T" :
                return new JunoTodo(description, isDone);
            case "D" :
                String by = parts[3];
                return new JunoDeadline(description, isDone, by);
            case "E" :
                String from = parts[3];
                String to = parts[4];
                return new JunoEvent(description, isDone, from, to);
            default :
                throw new JunoException(INVALID_TYPE_ERROR, line);
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}