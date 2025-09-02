package v.parser;

import org.junit.jupiter.api.Test;
import command.*;
import parser.Parser;
import task.DukeException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_todo_returnsAddTodoCommand() throws DukeException {
        assertTrue(Parser.parse("todo buy milk") instanceof AddTodoCommand);
    }

    @Test
    public void parse_mark_returnsMarkCommand() throws DukeException {
        assertTrue(Parser.parse("mark 2") instanceof MarkCommand);
    }

    @Test
    public void parse_deadline_returnsAddDeadlineCommand() throws DukeException {
        assertTrue(Parser.parse("deadline return book /by 2025-10-01") instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_event_returnsAddEventCommand() throws DukeException {
        assertTrue(Parser.parse("event project meeting /from Mon 2pm /to 4pm") instanceof AddEventCommand);
    }

    @Test
    public void parse_unknownCommand_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("blahrg"));
    }

    @Test
    public void parse_todoMissingDescription_throwsDukeException() {
        assertThrows(DukeException.class, () -> Parser.parse("todo"));
    }
}
