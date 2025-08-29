package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import task.Task;
import task.DukeException;

/**
 * Command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates a new UnmarkCommand.
     *
     * @param index The index of the task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.unmarkAsDone(index);
            ui.showLine();
            System.out.println("     Undone, for now. Marked as not done:");
            System.out.println("       " + task);
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (Exception e) {
            ui.showError("An error occurred while unmarking the task.");
        }
        return false;
    }
}
