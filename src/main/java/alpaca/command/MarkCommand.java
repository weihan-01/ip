package alpaca.command;

import alpaca.Storage;
import alpaca.task.Task;
import alpaca.TaskList;
import alpaca.Ui;

public class MarkCommand extends Command {
    private final String arg;
    private final boolean isMark;

    public MarkCommand(String arg, boolean isMark) {
        this.arg = arg;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(arg.trim()) - 1;
            Task t = tasks.get(index);
            if (isMark) {
                t.markAsDone();
                ui.showMessage("Nice! I've marked this task as done:\n  " + t);
            } else {
                t.markAsNotDone();
                ui.showMessage("OK! I've marked this task as not done yet:\n  " + t);
            }
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            ui.showMessage("Oops! That task number doesn't exist.");
        }
    }
}