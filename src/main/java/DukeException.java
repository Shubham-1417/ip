
/**
 * Represents user-facing errors in the chatbot (input/command issues).
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
