package v.task;

import org.junit.jupiter.api.Test;
import v.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void mark_unmark_toggleStatusIconAndString() {
        Todo t = new Todo("read book");
        // initially not done
        assertEquals(" ", t.getStatusIcon(), "New task should not be done");
        assertTrue(t.toString().startsWith("[T][ ]"), "String should reflect not done");

        // mark done
        t.mark();
        assertEquals("X", t.getStatusIcon(), "After mark, status icon should be X");
        assertTrue(t.toString().startsWith("[T][X]"), "String should reflect done");

        // unmark back
        t.unmark();
        assertEquals(" ", t.getStatusIcon(), "After unmark, status should be blank again");
        assertTrue(t.toString().startsWith("[T][ ]"), "String should reflect not done again");
    }
}