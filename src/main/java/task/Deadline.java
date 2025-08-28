package task;

import enums.TaskType;

/**
 * A task that needs to be done before a specific date/time.
 * A promise with a deadline, a race against time itself.
 */
public class Deadline extends Task {
    protected final String by;

    /**
     * Creates a new Deadline task with description and due date/time.
     * 
     * @param description The purpose of this task
     * @param by When this task must be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type of this task.
     * 
     * @return The TaskType.DEADLINE enum value
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns a string representation of this deadline task.
     * 
     * @return Formatted string with deadline details
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    
    @Override
    public String toSaveString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by;
    }
}
