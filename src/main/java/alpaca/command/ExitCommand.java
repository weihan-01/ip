package alpaca.command;

import alpaca.Storage;
import alpaca.TaskList;
import alpaca.Ui;
import javafx.application.Platform;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        Platform.exit();  // closes the GUI
        return "Goodbye!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
