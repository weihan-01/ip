package alpaca;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Huh? I don't speak gibberish! Try something I actually understand.");
    }
}