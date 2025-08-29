package v.command;

import v.task.TaskList;
import v.ui.Ui;
import v.storage.Storage;

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
