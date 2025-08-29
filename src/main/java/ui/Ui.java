package ui;

import task.Task;
import task.TaskList;
import java.util.Scanner;

/**
 * Handles all user interface interactions.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final String LOGO = "Voil\u00e0! In view, a voice of the vox populi.\n" +
            "\n" +
            "Behold\u2014the visage behind the letter.\n";
    private final Scanner scanner;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println("I am V. Voice for the voiceless. What do you require?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows a horizontal line separator.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Shows an error message.
     *
     * @param message The error message to display
     */
    public void showError(String message) {
        System.out.println("     " + message);
    }

    /**
     * Shows a message when a task is added.
     *
     * @param task The task that was added
     * @param size The new size of the task list
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("     Got it. I have inscribed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + size + " tasks in the list.");
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The list of tasks to display
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("     Your current conspiracies (tasks):");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Shows a goodbye message when the application exits.
     */
    public void showGoodbye() {
        System.out.println("     The curtain descends, everything ends too soon, too soon.");
        System.out.println("     Beneath this mask there is more than flesh. Beneath this mask there is an idea.");
        System.out.println("     And ideas are bulletproof!");
        System.out.println("     Farewell. May we meet again in the shadows.");
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user
     */
    public String readCommand() {
        System.out.print("     What is your command? ");
        return scanner.nextLine().trim();
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        showError("Error loading tasks. Starting with an empty task list.");
    }
}
