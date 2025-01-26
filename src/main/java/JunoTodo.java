public class JunoTodo extends JunoTask {
    public JunoTodo(String description) {
        super(description);
    }

    public JunoTodo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone() ? "1" : "0") + " | " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}