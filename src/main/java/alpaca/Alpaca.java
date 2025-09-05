package alpaca;

/**
 * The main entry point for the Alpaca chatbot application.
 * Handles initialization and the main command loop.
 */
public class Alpaca {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Alpaca(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        new Alpaca("data/duke.txt").run();
    }
}
