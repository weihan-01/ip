package alpaca.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        // expects format: yyyy-MM-dd HHmm (e.g., 2019-12-02 1800)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toSaveFormat() {
        // Save in ISO_LOCAL_DATE_TIME format for easier parsing
        DateTimeFormatter saveFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | "
                + from.format(saveFmt) + " | " + to.format(saveFmt);
    }

    @Override
    public String toString() {
        DateTimeFormatter displayFmt = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        return "[E]" + super.toString() + " (from: " + from.format(displayFmt)
                + " to: " + to.format(displayFmt) + ")";
    }
}
