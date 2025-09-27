package alpaca.command;

import alpaca.Storage;
import alpaca.TaskList;
import alpaca.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Huh? I don't speak gibberish! Try something I actually understand.");
    }
}