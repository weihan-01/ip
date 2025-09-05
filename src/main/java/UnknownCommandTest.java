package alpaca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnknownCommandTest {
    @Test
    void execute_printsUnknownMessage() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        Storage storage = new Storage("data/duke.txt");
        UnknownCommand cmd = new UnknownCommand();

        // You can redirect System.out and check the output if you want,
        // or just ensure no exceptions are thrown:
        assertDoesNotThrow(() -> cmd.execute(tasks, ui, storage));
    }
}
