public class JunoDeadline extends JunoTask{
    protected String by;

    public JunoDeadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by : " + by + ")";
    }
}
