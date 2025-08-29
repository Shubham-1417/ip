package v.storage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import v.task.Task;
import v.task.Todo;
import v.task.TaskList;
import v.task.DukeException;

public class StorageTest {
    @Test
    public void testSaveAndLoad() throws DukeException {
        // Setup test file
        String testFilePath = "test_data.txt";
        Storage storage = new Storage(testFilePath);
        
        try {
            // Create test tasks
            List<Task> tasksToSave = List.of(
                new Todo("Test todo 1"),
                new Todo("Test todo 2")
            );
            
            // Save tasks
            storage.save(tasksToSave);
            
            // Verify file was created
            File file = new File(testFilePath);
            assertTrue(file.exists(), "File should be created after save");
            
            // Load tasks
            List<Task> loadedTasks = storage.load();
            
            // Verify loaded tasks
            assertEquals(2, loadedTasks.size(), "Should load 2 tasks");
            assertEquals("Test todo 1", loadedTasks.get(0).getDescription());
            assertEquals("Test todo 2", loadedTasks.get(1).getDescription());
            
        } finally {
            // Clean up
            File file = new File(testFilePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
