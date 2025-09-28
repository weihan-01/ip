package alpaca.command;

import alpaca.Storage;
import alpaca.TaskList;
import alpaca.Ui;

public class UnknownCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = "Huh? I don't speak gibberish! Try something I actually understand.";
        ui.showMessage(msg);
        return msg;
    }
}
