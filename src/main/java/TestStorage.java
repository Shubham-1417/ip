import storage.Storage;
import task.TaskList;
import task.Todo;
import task.DukeException;
import java.io.File;

public class TestStorage {
    public static void main(String[] args) {
        try {
            // Test saving and loading tasks
            String testFile = "test_storage.txt";
            Storage storage = new Storage(testFile);
            
            // Create some test tasks
            TaskList tasks = new TaskList();
            tasks.add(new Todo("Test task 1"));
            tasks.add(new Todo("Test task 2"));
            
            // Save tasks
            System.out.println("Saving tasks...");
            storage.save(tasks);
            System.out.println("Tasks saved successfully!");
            
            // Load tasks
            System.out.println("Loading tasks...");
            TaskList loadedTasks = storage.load();
            System.out.println("Tasks loaded successfully!");
            
            // Verify loaded tasks
            System.out.println("\nLoaded tasks:");
            for (int i = 0; i < loadedTasks.size(); i++) {
                System.out.println((i + 1) + ". " + loadedTasks.get(i));
            }
            
            // Clean up
            new File(testFile).delete();
            
        } catch (DukeException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
