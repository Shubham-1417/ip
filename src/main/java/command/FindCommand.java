package command;

import task.Task;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.util.List;

/**
 * Command to find tasks containing a keyword (case-insensitive).
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.find(keyword);
        ui.showFindResults(matches);
        return false;
    }
}
