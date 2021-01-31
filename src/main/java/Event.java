import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public Event markAsDone() {
        return new Event(this.description, true, this.time);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (at: " + time.format(formatter) + ")";
    }
}
