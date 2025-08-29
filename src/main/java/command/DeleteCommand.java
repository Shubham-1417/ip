package command;

import task.TaskList;
import task.Task;
import task.DukeException;
import ui.Ui;
import storage.Storage;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new DeleteCommand.
     *
     * @param index The index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.remove(index);
            ui.showLine();
            System.out.println("     Very well. I've removed this task from the records:");
            System.out.println("       " + removedTask);
            System.out.println("     Now you have " + tasks.size() + " task" + (tasks.size() != 1 ? "s" : "") + " in the list.");
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            ui.showError("No such task exists. The number must be between 1 and " + tasks.size());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }
}
