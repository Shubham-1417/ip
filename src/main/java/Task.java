/**
 * A simple task with a description and a done/not-done status.
 * Each task is a solemn vow, a promise to be fulfilled or broken.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a new task, unfulfilled, waiting in the wings.
     * 
     * @param description The purpose that gives this task meaning
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** 
     * Marks this task as completed, a promise fulfilled.
     * The deed is done, the vow upheld.
     */
    public void mark() {
        this.isDone = true;
    }

    /** 
     * Returns this task to its unfulfilled state.
     * The wheel turns back, the promise yet to be kept.
     */
    public void unmark() {
        this.isDone = false;
    }

    /** 
     * Reveals the status of this task - a cross for completion,
     * a space for what's yet to be done.
     * 
     * @return "X" if done, " " if not
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the type tag for this task (T for Todo, D for Deadline, E for Event).
     * 
     * @return A single character string representing the task type
     */
    public abstract String getTypeTag();
    
    /** 
     * Presents this task as a dramatic proclamation.
     * 
     * @return A string in the form of [T][X] task description
     */
    @Override
    public String toString() {
        return "[" + getTypeTag() + "][" + getStatusIcon() + "] " + description;
    }
}
