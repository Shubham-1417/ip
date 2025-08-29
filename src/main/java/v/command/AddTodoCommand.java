package v.command;

import v.task.TaskList;
import v.task.Task;
import v.task.Todo;
import v.task.DukeException;
import v.ui.Ui;
import v.storage.Storage;

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
