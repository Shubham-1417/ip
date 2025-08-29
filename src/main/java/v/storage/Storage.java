package v.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import v.task.Task;
import v.task.TaskList;
import v.task.Todo;
import v.task.Deadline;
import v.task.Event;
import v.task.DukeException;

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
     * Loads tasks from the storage file into a TaskList.
     * 
     * @return A TaskList containing the loaded tasks
     * @throws DukeException If there's an error reading the file
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        File file = new File(filePath).getAbsoluteFile();
        System.out.println("Attempting to load tasks from: " + file.getAbsolutePath());
        
        // If file doesn't exist, return empty task list
        if (!file.exists()) {
            System.out.println("No existing data file found. Starting with an empty task list.");
            return tasks;
        }
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
        } catch (IOException e) {
            throw new DukeException("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }
    
    /**
     * Saves tasks to the storage file.
     * 
     * @param tasks The TaskList containing tasks to save
     * @throws DukeException If there's an error writing to the file
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            File file = new File(filePath).getAbsoluteFile();
            System.out.println("Saving tasks to: " + file.getAbsolutePath());
            
            // Ensure parent directory exists
            File parent = file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            
            try (FileWriter fw = new FileWriter(file)) {
                for (int i = 0; i < tasks.size(); i++) {
                    fw.write(tasks.get(i).toSaveString() + System.lineSeparator());
                }
            }
            
            System.out.println("Successfully saved " + tasks.size() + " tasks.");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
            e.printStackTrace();
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }
}
