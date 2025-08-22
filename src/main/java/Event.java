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
     * @param description The nature of this engagement
     * @param from When this event begins
     * @param to When this event concludes
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type tag for this task, 'E' for Event.
     * 
     * @return "E" representing an Event task
     */
    @Override
    public String getTypeTag() { 
        return "E"; 
    }

    /**
     * Returns a string representation of this event task.
     * 
     * @return Formatted string with event timing details
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
