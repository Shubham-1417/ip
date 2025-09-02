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

    /**
     * Constructs a {@code FindCommand} that searches for tasks with descriptions
     * containing the given keyword (case-insensitive).
     *
     * @param keyword the search term to match within task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes this command by filtering tasks in {@code TaskList} that contain the keyword
     * and displaying them via {@code Ui}.
     *
     * @param tasks   the current list of tasks
     * @param ui      the UI used to display results
     * @param storage the storage (unused)
     * @return false to continue running the program
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matches = tasks.find(keyword);
        ui.showFindResults(matches);
        return false;
    }
}
