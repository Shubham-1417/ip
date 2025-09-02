import command.Command;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import task.DukeException;
import ui.Ui;

/**
 * V - A personal assistant with a theatrical flair, inspired by V for Vendetta.
 * This program greets the user, manages tasks, and bids them farewell.
 */
public class V {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for V. Initializes the UI, storage, and loads tasks from storage.
     */
    public V() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
        
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
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
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        new V().run();
    }
}
