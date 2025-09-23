package alpaca.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() { this.isDone = true; }
    public void markAsNotDone() { this.isDone = false; }

    public String getStatusIcon() { return isDone ? "X" : " "; }

    public String toSaveFormat() {
        // Default implementation, can be overridden by subclasses
        return "";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the description of the task.
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }
}