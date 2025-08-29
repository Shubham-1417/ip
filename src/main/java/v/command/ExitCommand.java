package v.command;

import v.task.TaskList;
import v.ui.Ui;
import v.storage.Storage;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        return true;
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
