package v.task;

import v.enums.TaskType;

/**
 * A task that starts at a specific time and ends at a specific time.
 * A performance in the grand play of your schedule.
 */
public class Event extends Task {
    protected final String from;
    protected final String to;

    /**
     * Creates a new Event task with description, start and end times.
     *
     * @param description The nature of this engagement.
     * @param from When this event begins.
     * @param to When this event concludes.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type of this task.
     *
     * @return The TaskType.EVENT enum value.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Returns a string representation of this event task.
     *
     * @return Formatted string with event timing details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the serialized representation of this event for persistence.
     * Format: {@code E | <done:1|0> | <description> | <from> | <to>}.
     *
     * @return A save-friendly string for this event.
     */
    @Override
    public String toSaveString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}
