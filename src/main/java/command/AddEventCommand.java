package command;

import task.TaskList;
import task.Event;
import task.Task;
import task.DukeException;
import ui.Ui;
import storage.Storage;

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
     * @param description The description of the event
     * @param from The start time of the event
     * @param to The end time of the event
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
     * @param tasks   the list to update
     * @param ui      the UI to display feedback
     * @param storage the storage used to persist changes
     * @return false to continue running the program
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
