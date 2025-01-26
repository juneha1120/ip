public class JunoDeadline extends JunoTask{
    protected String by;

    public JunoDeadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public JunoDeadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + this.getDescription() + " | " + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by : " + by + ")";
    }
}