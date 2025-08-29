package v.command;

import v.task.TaskList;
import v.task.Deadline;
import v.task.Task;
import v.task.DukeException;
import v.ui.Ui;
import v.storage.Storage;
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
