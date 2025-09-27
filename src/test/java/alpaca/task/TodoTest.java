package alpaca.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToSaveFormat_NotDone() {
        Todo todo = new Todo("buy milk");
        String expected = "T | 0 | buy milk";
        assertEquals(expected, todo.toSaveFormat());
    }

    @Test
    void testToSaveFormat_Done() {
        Todo todo = new Todo("walk dog");
        todo.markAsDone(); // assuming Task has this method
        String expected = "T | 1 | walk dog";
        assertEquals(expected, todo.toSaveFormat());
    }

    @Test
    void testToString() {
        Todo todo = new Todo("read book");
        String result = todo.toString();
        assertTrue(result.startsWith("[T]"));
        assertTrue(result.contains("read book"));
    }
}
