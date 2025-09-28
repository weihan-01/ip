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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();

        try {
            int index = Integer.parseInt(arg.trim()) - 1;
            Task t = tasks.get(index);

            if (isMark) {
                t.markAsDone();
                String msg = "Nice! I've marked this task as done:\n  " + t;
                response.append(msg);
                ui.showMessage(msg);
            } else {
                t.markAsNotDone();
                String msg = "OK! I've marked this task as not done yet:\n  " + t;
                response.append(msg);
                ui.showMessage(msg);
            }

            storage.save(tasks.getTasks());

        } catch (Exception e) {
            String error = "Oops! That task number doesn't exist.";
            response.append(error);
            ui.showMessage(error);
        }

        return response.toString();
    }
}
