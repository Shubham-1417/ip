package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes this command by displaying all tasks in the list.
     *
     * @param tasks   the task list to display
     * @param ui      the UI to render the list
     * @param storage the storage (unused)
     * @return false to continue running the program
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
        return false;
    }
}
