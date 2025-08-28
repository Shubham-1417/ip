package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import task.DukeException;

/**
 * Handles loading and saving tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage instance with a simple file path in the current directory.
     */
    public Storage() {
        this("v_data.txt");
        System.out.println("Using data file: " + new File("v_data.txt").getAbsolutePath());
    }

    /**
     * Creates a new Storage instance with a custom file path.
     * 
     * @param filePath The path to the file where tasks will be stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * 
     * @return A list of tasks
     * @throws DukeException If there's an error reading the file
     */
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath).getAbsoluteFile();
        Path path = file.toPath();
        
        System.out.println("Loading tasks from: " + path);
        
        if (!file.exists()) {
            System.out.println("File does not exist, starting with empty task list");
            return tasks;
        }
        
        try {
            List<String> lines = Files.readAllLines(path);
            
            for (String line : lines) {
                // Split on pipe character with optional whitespace
                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length < 3) continue;
                
                String type = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();
                
                Task task;
                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        if (parts.length < 4) continue;
                        try {
                            LocalDate date = LocalDate.parse(parts[3].trim());
                            task = new Deadline(description, date);
                        } catch (Exception e) {
                            System.err.println("Error parsing date: " + parts[3].trim());
                            continue;
                        }
                        break;
                    case "E":
                        if (parts.length < 5) continue;
                        task = new Event(description, parts[3].trim(), parts[4].trim());
                        break;
                    default:
                        continue;
                }
                
                if (isDone) {
                    task.mark();
                }
                
                tasks.add(task);
            }
            
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error reading from file: " + e.getMessage());
        }
    }
    
    /**
     * Saves tasks to the storage file.
     * 
     * @param tasks The list of tasks to save
     * @throws DukeException If there's an error writing to the file
     */
    public void save(List<Task> tasks) throws DukeException {
        try {
            File file = new File(filePath).getAbsoluteFile();
            System.out.println("Saving tasks to: " + file.getAbsolutePath());
            
            // Ensure parent directory exists
            File parent = file.getParentFile();
            if (parent != null) {
                System.out.println("Creating parent directory: " + parent.getAbsolutePath());
                if (!parent.exists() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory: " + parent.getAbsolutePath());
                }
            }
            
            System.out.println("Writing " + tasks.size() + " tasks to file");
            try (FileWriter writer = new FileWriter(file)) {
                for (Task task : tasks) {
                    writer.write(task.toSaveString() + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error writing to file: " + e.getMessage());
        }
    }
}
