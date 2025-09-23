package alpaca.command;

import alpaca.*;
import alpaca.task.Deadline;
import alpaca.task.Event;
import alpaca.task.Task;
import alpaca.task.Todo;

public class AddCommand extends Command {
    private final String type;
    private final String args;

    public AddCommand(String type, String args) {
        this.type = type;
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t;
            switch (type) {
                case "todo":
                    if (args.trim().isEmpty()) throw new Exception();
                    t = new Todo(args.trim());
                    break;
                case "deadline": {
                    String[] parts = args.split("/by");
                    if (parts.length < 2 || parts[0].trim().isEmpty()) throw new Exception();
                    t = new Deadline(parts[0].trim(), parts[1].trim());
                    break;
                }
                case "event": {
                    String[] parts = args.split("/from");
                    if (parts.length < 2 || parts[0].trim().isEmpty()) throw new Exception();
                    String name = parts[0].trim();
                    String[] times = parts[1].split("/to");
                    if (times.length < 2) throw new Exception();
                    t = new Event(name, times[0].trim(), times[1].trim());
                    break;
                }
                default:
                    throw new Exception();
            }
            tasks.add(t);
            storage.save(tasks.getTasks());
            ui.showMessage("Got it. I've added this task:\n  " + t +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            ui.showMessage("Invalid format or missing description/times.");
        }
    }
}