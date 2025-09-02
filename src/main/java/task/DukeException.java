
package task;

/**
 * Represents user-facing errors in the chatbot (input/command issues).
 */
public class DukeException extends Exception {
    /**
     * Creates a new {@code DukeException} with a user-facing message.
     *
     * @param message the descriptive error shown to the user
     */
    public DukeException(String message) {
        super(message);
    }
}
