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

    /**
     * Executes this command by creating a new {@code Todo} and adding it to the list.
     * Also persists the updated task list using {@code Storage}.
     *
     * @param tasks   the task list to mutate
     * @param ui      the UI to display messages
     * @param storage the storage to persist changes
     * @return false to indicate the application should continue running
     */
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
