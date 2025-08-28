package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

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
