package v;

import v.command.Command;
import v.parser.Parser;
import v.storage.Storage;
import v.task.DukeException;
import v.task.TaskList;
import v.ui.Ui;

/**
 * V - A personal assistant with a theatrical flair, inspired by V for Vendetta.
 * This program greets the user, manages tasks, and bids them farewell.
 */
public class V {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private String commandType;

    /**
     * Constructor for V. Initializes the UI, storage, and loads tasks from storage.
     */
    public V() {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Generates a response for the user's chat message.
     * This method integrates with the existing V logic.
     */
    public String getResponse(String input) {
        commandType = null; // Reset command type
        try {
            Command c = Parser.parse(input);
            commandType = c.getClass().getSimpleName();
            
            StringBuilder response = new StringBuilder();
            Ui tempUi = createResponseUi(response);

>>>>>>> master
            c.execute(tasks, tempUi, storage);
            storage.save(tasks);

            if (response.length() == 0) {
                response.append("Command executed successfully.");
            }

            return response.toString();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (Exception e) {
            // Handle unexpected runtime exceptions gracefully
            System.err.println("Unexpected error processing command: " + e.getMessage());
            return "An unexpected error occurred. The shadows whisper of technical difficulties: " + e.getMessage();
        }
    }

    /**
     * Creates a temporary UI implementation that captures responses in a StringBuilder.
     * This method improves code readability by extracting the anonymous UI class.
     *
     * @param response The StringBuilder to capture UI responses.
     * @return A Ui implementation that writes to the StringBuilder.
     */
    private Ui createResponseUi(StringBuilder response) {
        return new Ui() {
            @Override
            public void showTaskAdded(v.task.Task task, int size) {
                response.append("Excellent. I have inscribed this task into our conspiracy:\n");
                response.append("  ").append(task).append("\n");
                response.append("Now you have ").append(size).append(" task")
                        .append(size != 1 ? "s" : "").append(" in the revolutionary agenda.");
            }

            @Override
            public void showTaskList(TaskList tasks) {
                if (tasks.isEmpty()) {
                    response.append("The stage is set, but the script is blank. "
                            + "Your revolutionary agenda awaits its first act.");
                    return;
                }
                response.append("Behold, your current conspiracies against the mundane:\n\n");
                for (int i = 0; i < tasks.size(); i++) {
                    response.append("  ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                }
                response.append("\nTotal acts of rebellion: ").append(tasks.size()).append(" task(s).");
            }

            @Override
            public void showError(String message) {
                response.append(transformErrorToVStyle(message));
            }

            @Override
            public void showGoodbye() {
                response.append("The curtain descends, everything ends too soon, too soon.\n");
                response.append("Beneath this mask there is more than flesh. ")
                        .append("Beneath this mask there is an idea.\n");
                response.append("And ideas are bulletproof!\n");
                response.append("Farewell. May we meet again in the shadows.");
            }

            @Override
            public void showFindResults(java.util.List<v.task.Task> matches) {
                if (matches.isEmpty()) {
                    response.append("The search yields nothing. Even the shadows are silent on this matter.");
                } else {
                    response.append("Voilà! The shadows reveal these conspiracies:\n\n");
                    for (int i = 0; i < matches.size(); i++) {
                        response.append("  ").append(i + 1).append(". ").append(matches.get(i)).append("\n");
                    }
                    response.append("\nUnearthed from the depths: ").append(matches.size())
                            .append(" matching revelation(s).");
                }
            }
        };
    }

    /**
     * Transforms generic error messages into V's dramatic style.
     * This method improves code maintainability by centralizing error message transformation.
     *
     * @param message The original error message.
     * @return A V-themed error message.
     */
    private String transformErrorToVStyle(String message) {
        if (message.contains("description of a todo cannot be empty")) {
            return "Even ideas need words. The description of a todo cannot be empty.";
        } else if (message.contains("description of a deadline cannot be empty")) {
            return "Time waits for no one, but words are required. "
                    + "The description of a deadline cannot be empty.";
        } else if (message.contains("description of an event cannot be empty")) {
            return "Every revolution needs a purpose. "
                    + "The description of an event cannot be empty.";
        } else if (message.contains("OOPS!!! I'm afraid I don't know what that means")) {
            return "Even silence speaks volumes. But I need words to understand you.";
        } else {
            return "Alas, the shadows whisper of an error: " + message;
        }
    }

    /**
     * Gets the command type of the last executed command.
     *
     * @return The command type as a string.
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Runs the V CLI application loop.
     * Reads commands from {@code Ui}, parses them into {@code Command}s, and executes them
     * until an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main entry point of the V CLI application.
     * Orchestrates parsing, execution, and persistence of tasks.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        new V().run();
    }
}
