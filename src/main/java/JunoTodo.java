public class JunoTodo extends JunoTask {
    public JunoTodo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
