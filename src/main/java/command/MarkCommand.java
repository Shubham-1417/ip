package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;
import task.Task;
import task.DukeException;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a new MarkCommand.
     *
     * @param index The index of the task to mark as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markAsDone(index);
            ui.showLine();
            System.out.println("     A tick for triumph. Marked as done:");
            System.out.println("       " + task);
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (Exception e) {
            ui.showError("An error occurred while marking the task as done.");
        }
        return false;
    }
}
