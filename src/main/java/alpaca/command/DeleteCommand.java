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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(arg.trim()) - 1;
            Task removed = tasks.remove(index);
            storage.save(tasks.getTasks());
            ui.showMessage("Noted. I've removed this task:\n  " + removed +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            ui.showMessage("Oops! That task number doesn't exist.");
        }
    }
}