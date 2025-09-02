package command;

import task.TaskList;
import task.Deadline;
import task.Task;
import task.DukeException;
import ui.Ui;
import storage.Storage;
import java.time.LocalDate;

/**
 * Command to add a new deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    /**
     * Creates a new AddDeadlineCommand.
     *
     * @param description The description of the deadline task
     * @param by The deadline date
     */
    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes this command by creating a new {@code Deadline} and adding it to the list,
     * then persisting the updated list.
     *
     * @param tasks   the task list to update
     * @param ui      the UI to display feedback
     * @param storage the storage used to persist changes
     * @return false to continue running the program
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = new Deadline(description, by);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks.size());
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }
}
