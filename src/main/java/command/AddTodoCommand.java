package command;

import task.TaskList;
import task.Task;
import task.Todo;
import task.DukeException;
import ui.Ui;
import storage.Storage;

/**
 * Command to add a new todo task.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Creates a new AddTodoCommand.
     *
     * @param description The description of the todo task
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new Todo(description);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }
}
