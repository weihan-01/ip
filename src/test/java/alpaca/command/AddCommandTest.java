package alpaca.command;

import alpaca.*;
import alpaca.task.TaskList;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tasks = new TaskList();
        ui = new Ui();

        // Create a unique temporary file inside data folder
        Path dataDir = Paths.get("data");
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
        tempFile = dataDir.resolve("tasks_test_" + System.currentTimeMillis() + ".txt");
        Files.createFile(tempFile);

        storage = new Storage(tempFile.toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testAddTodo_Success() {
        AddCommand cmd = new AddCommand("todo", "read book");
        cmd.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.getTasks().get(0).toString().contains("read book"));
        assertTrue(ui.getLastMessage().contains("Got it. I've added this task"));
    }

    @Test
    void testAddTodo_MissingDescription() {
        AddCommand cmd = new AddCommand("todo", "   ");
        cmd.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
        assertTrue(ui.getLastMessage().contains("Invalid format or missing description/times"));
    }

    @Test
    void testAddDeadline_Success() {
        AddCommand cmd = new AddCommand("deadline", "submit report /by 2025-09-30");
        cmd.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.getTasks().get(0).toString().contains("submit report"));
        assertTrue(ui.getLastMessage().contains("Got it. I've added this task"));
    }

    @Test
    void testAddDeadline_Invalid() {
        AddCommand cmd = new AddCommand("deadline", "submit report"); // missing /by
        cmd.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
        assertTrue(ui.getLastMessage().contains("Invalid format or missing description/times"));
    }

    @Test
    void testAddEvent_Success() {
        AddCommand cmd = new AddCommand("event", "meeting /from 2025-09-27 1400 /to 2025-09-27 1600");
        cmd.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertTrue(tasks.getTasks().get(0).toString().contains("meeting"));
        assertTrue(ui.getLastMessage().contains("Got it. I've added this task"));
    }

    @Test
    void testAddEvent_Invalid_MissingTo() {
        AddCommand cmd = new AddCommand("event", "party /from 2025-09-27 1400"); // missing /to
        cmd.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
        assertTrue(ui.getLastMessage().contains("Invalid format or missing description/times"));
    }

    @Test
    void testAddEvent_Invalid_MissingFrom() {
        AddCommand cmd = new AddCommand("event", "party"); // missing /from
        cmd.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
        assertTrue(ui.getLastMessage().contains("Invalid format or missing description/times"));
    }

    @Test
    void testInvalidType() {
        AddCommand cmd = new AddCommand("unknown", "blah");
        cmd.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
        assertTrue(ui.getLastMessage().contains("Invalid format or missing description/times"));
    }
}
