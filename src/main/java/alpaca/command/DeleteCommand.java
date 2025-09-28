package alpaca.command;

import alpaca.Storage;
import alpaca.task.Task;
import alpaca.TaskList;
import alpaca.Ui;

public class DeleteCommand extends Command {
    private final String arg;

    public DeleteCommand(String arg) {
        this.arg = arg;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(arg.trim()) - 1;
            Task removed = tasks.remove(index);
            storage.save(tasks.getTasks());
            String msg = "Noted. I've removed this task:\n  " + removed +
                    "\nNow you have " + tasks.size() + " tasks in the list.";
            ui.showMessage(msg);
            return msg;
        } catch (Exception e) {
            String msg = "Oops! That task number doesn't exist.";
            ui.showMessage(msg);
            return msg;
        }
    }
}