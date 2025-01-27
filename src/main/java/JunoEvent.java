import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JunoEvent extends JunoTask{
    protected LocalDateTime from;
    protected LocalDate fromDate;
    protected LocalDateTime to;
    protected LocalDate toDate;

    public JunoEvent(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.fromDate = from.toLocalDate();
        this.to = to;
        this.toDate = to.toLocalDate();
    }

    public JunoEvent(String description, boolean isDone, LocalDate fromDate, LocalDate toDate) {
        super(description, isDone);
        this.from = null;
        this.fromDate = fromDate;
        this.to = null;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone() ? "1" : "0") + " | " + this.getDescription() + " | " +
                (this.from == null ? this.fromDate : this.from) + " | " +
                (this.to == null ? this.toDate : this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " +
                (this.from == null ? this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) :
                        this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"))) +
                " to " + (this.to == null ? this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) :
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mma"))) + ")";
    }
}