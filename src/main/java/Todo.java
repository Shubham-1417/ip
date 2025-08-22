/**
 * A task without any date/time attached to it.
 * A simple vow, unbound by time's cruel march.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo task with the given description.
     * 
     * @param description The purpose that gives this task meaning
     */
    public Todo(String description) { 
        super(description); 
    }
    
    /**
     * Returns the type tag for this task, 'T' for Todo.
     * 
     * @return "T" representing a Todo task
     */
    @Override 
    public String getTypeTag() { 
        return "T"; 
    }
}
