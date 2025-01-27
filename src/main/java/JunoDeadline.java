import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JunoDeadline extends JunoTask{
    protected LocalDateTime by;
    protected LocalDate byDate;

    public JunoDeadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
        this.byDate = by.toLocalDate();
    }

    public JunoDeadline(String description, boolean isDone, LocalDate byDate) {
        super(description, isDone);
        this.by = null;
        this.byDate = byDate;
    }

    public LocalDate getByDate() {
        return byDate;
    }

    @Override
    public String toFileFormat() {
        return "D | " + (isDone() ? "1" : "0") + " | " + this.getDescription() + " | " +
                (this.by == null ? this.byDate : this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " +
                (this.by == null ? this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) :
                        this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"))) + ")";
    }
}