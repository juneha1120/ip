public class JunoEvent extends JunoTask{
    protected String from;
    protected String to;

    public JunoEvent(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public JunoEvent(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + this.getDescription() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from : " + from + " to : " + to + ")";
    }
}