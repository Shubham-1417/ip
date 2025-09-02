package v.command;

import v.storage.Storage;
import v.task.DukeException;
import v.task.Event;
import v.task.Task;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Command to add a new event task.
 */
public class AddEventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Creates a new AddEventCommand.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes this command by creating a new {@code Event} and adding it to the list,
     * then persisting the updated task list.
     *
     * @param tasks   The list to update.
     * @param ui      The UI to display feedback.
     * @param storage The storage used to persist changes.
     * @return False to continue running the program.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new Event(description, from, to);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }
}
