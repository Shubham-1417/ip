package command;

import task.TaskList;
import ui.Ui;
import storage.Storage;

/**
 * Command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
        return false;
    }
}
