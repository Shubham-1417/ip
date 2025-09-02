package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by showing a goodbye message.
     *
     * @param tasks   the task list (unused)
     * @param ui      the UI to display the farewell
     * @param storage the storage (unused)
     * @return true to indicate the application should exit
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        return true;
    }
    
    /**
     * Indicates that this command should terminate the application.
     *
     * @return true always
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
