import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import enums.CommandType;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;
import task.DukeException;

/**
 * V - A personal assistant with a theatrical flair, inspired by V for Vendetta.
 * This program greets the user, manages tasks, and bids them farewell.
 */
public class V {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    
    // Delimiters used in string parsing
    private static final String DELIMITER_BY = "/by";
    private static final String DELIMITER_FROM = "/from";
    private static final String DELIMITER_TO = "/to";
    private static final String DIVIDER = "    ____________________________________________________________\n";
    private static final String LOGO = 
    "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%##*#%##%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@%@@@@*%#%%@@%%%%%%%%%%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@+*+:=+%@@@@@@@@@@%%%%%%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%@@@@@@@@@%@@@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%@%@@@@@@%@@@@@@@@@@@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%\n"
    + "%%%%%%%%%%%%%%%%%%%@%@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%@@@@@@@@@@@#*+:....::+*++=--:-----===+#%@@@@@@@@@@@@@@@@@%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%%@@@@@@:....:::.....:.::::::::::::::-==+**#@@@@@@@@@@@@@@%%%\n"
    + "%%%%%%%%%%%%%%%%%%%%@%@%%=...:::::::..:::::::::::::.:-+*****####*#@@@@@@@@@@@@%%\n"
    + "%%%%%%%%%%%%%%%%%@@%%%@#:.:.*%@+%@@@*+::::::::::..:=+*%@@@@@@@@@@%#%@@@@@@@@@@%%\n"
    + "%%%%%%%%%%%%%%%%@@%%%%@#:-=-::=+*+@%%@@@@::::::::::@@@@@%%%%**+***%%@@@@@@@@@@@%\n"
    + "%%%%%%%%%%%%%%%%%%%%@@%%:.......:::-++#@@@%::::::.@@@%%%#*===++++++#@@@@@@@@@@@%\n"
    + "%%%%%%%%%%%%%%%%%@%%%@%%........::::---++*-::::--+*%%%#*+===+++*+**%@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%%%%@%@@%:::::--***##+====-:...:=*#%%#**%%%@@@@%###%@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@%%%@@@%-:-+#@@@@@@@@@@%+:....:+*###@@@@@@@@@@%@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@%@%%@%@-.....:---=+=:::......:+**##%#++-=+**++++*%@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@@@%%@%%......................:+***##+--:::---==+*#%%%%@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@@%@@@@%......................:+++*##*=-----===++*#%@%@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%@@@%%%@@@%:.....................:+******++==-==++**##%@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%@@@@%@@@@@=:..:::::::...........:=*+*#**#**+++++**##%@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@@@%@@%@%-:::::-----=:........:=+******##%######%%@@@%@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%@@%%%@@@@+::-+**#=:.....=##:==+***%%#*****##%@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%@@%@%%@@@@-:-+-##-........:+=%@@@@%#******##@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%#@%%@@%%@@@@@@-:==.#@%+.....+%*#@%*@@@@@***#%@@@@@@@@@@@%@@@@@@@@@@\n"
    + "%%%%%#%%%#%%%%%%@%%%%%@@@@@%-:-=.:%@@@@@%%%%%-:::%@@@@@@@@@@%@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%#%%%%#%@%@%%@@@@@@@%#-.:=:....::+%%%@@@@@@@@%%%%%%#%%@@@@@@@@@@@@@@@@@@@\n"
    + "%%%#%#%#%%%%#%%%%%%%%@@@@@@@%*-.:--::.......::----=+++*###%%@@@@@@@@@@@@@@@@@@@@\n"
    + "%%#%%%%#%%%%%%%%@@%%%%@@@@@@@%*-...-....::--==+**########%%@@@@@@@@@%%@@@@@@@@@@\n"
    + "%%%%%%%#%#%%#%@%@@%%%%@@@@@@@@%+-...::.......-@@@#*+**###%@@@@@@@@@@%@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%@@@@%@%@@@@@@@@@@+::..........=@%@#*++*##%%%@@@@@@@@@%@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%@%%%@%@%@@@@@@@@@%-:.........+%@@@#*+*#%%%@@@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%%@@%@@@@@@@@@@@@@@@@-:.......#%@@@##**##%@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%%%%%%@@%@@@@@@@@@@@@@@@@+:.....+%@@@#**##%@@@@@@@%@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%%%%%%%%@%@@@%@@@@@@@@@@@@@@@@@@@@%=::.-%@@*#*##@@@@@@@@@%@@@%@@@@@@@@@@@@\n"
    + "%%%%#%%%%%@%%@%%%@%@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@%@@@@@@@@@@@@@@@@@%@@@@@@@@@@@@\n"
    + "%#%%#%%%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%@@@@@@@@@@@\n"
    + "#%%#%%%%@%@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "%##%%%%%%@@@%@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@@@@@@@@@@@\n"
    + "#%%%%@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "#%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "###%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@#@@%@@@%%%@@%%@@%@@@@@@@@@@@@@@@@@@@@\n"
    + "%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%@@%%%@@%%@@@@%@@@@@@%%@@@@@@@@@@@@@@@@@@@\n"
    + "%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%#%@@@%%%%%%%@@@@@@@@@@@%@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%#%@@%#######%@@@@@@@%%@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@@##%%%#%%%%%@@@@@@@@%%%@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%%%%%%@@@@@@@@@@%%@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@%%%%%@@@@@@%%%@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%#%%%%%%%%%@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%%##%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%+--+%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*+=%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%#@@@@%%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@@%#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@%@@@@@@@@@@@@@@@@@@@@%%%%%@@@@@@@@@@@@@%#%@@@@@@@@@@%%%%%%%%@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%@%%@@@@@@@@@@%#%@@@@@@@@%%%######%%@@@@@@@@@@\n"
    + "@@@@@@@@@@%@@@@@@@@@@@@@@@@@@@@@@@@@%@%%@@@@@@@@##@@@@@@@%%%######%@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%%%@@%@@@@@*#@@@@%#%%#%#####@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@%%%%%%%%@@@@@@@@@@@@%%%%@@@@@@@+%@@@%%%%%######@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@##%%%%%%%@@%%%%@@@@@@@%@@@@@@+@@@%%%%%######@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@%%##*####%%%%%%%%%%@@@@%@@@@@@+@@@@%%######%@@@@@@@@@@@@@@@@@@\n"
    + "@@@@@@@@@@@@@@@@@@@@#########%%%%%@@@@@@@@@@%%@@*@@@%######@@@@@@@@@@@@@@@@@@@@@\n";
    
    private static final String UNKNOWN_COMMAND = "A curious utterance. I do not recognize it. Try: todo, deadline, event, list, mark, unmark, bye";
    private static final String TODO_EMPTY = "Even ideas need words. The description of a todo cannot be empty.";
    private static final String DEADLINE_NEEDS_BY = "A deadline demands a /by. Example: deadline return book /by Sunday";
    private static final String DEADLINE_EMPTY = "Give it both flesh and hour: description and /by must not be empty.";
    private static final String EVENT_NEEDS_FROM_TO = "An event requires /from and /to. Example: event meet /from Mon 2pm /to 4pm";
    private static final String MARK_OK = "A tick for triumph. Marked as done:";
    private static final String UNMARK_OK = "Undone, for now. Marked as not done:";
    private static final String ADDED_HEADER = "Got it. I have inscribed this task:";
    private static final String LIST_HEADER = "Your current conspiracies (tasks):";
    private static final String EMPTY_INPUT = "Even silence speaks volumes. But I need words to understand you.";
    
    /**
     * The main entry point of the V program.
     * Displays the V logo, greets the user, processes user input in a loop,
     * and bids farewell when the user is done.
     *
     * @param args Command line arguments (not used)
     */
    /**
     * A custom PrintStream that removes ANSI escape sequences from all output.
     */
    private static class NoAnsiPrintStream extends PrintStream {
        // This pattern matches most common ANSI escape sequences
        private static final String ANSI_ESCAPE_PATTERN = "\\u001B\\[[\\d;]*[A-Za-z]";
        
        public NoAnsiPrintStream(OutputStream out) {
            super(out, true);
        }
        
        @Override
        public void print(String s) {
            if (s != null) {
                s = s.replaceAll(ANSI_ESCAPE_PATTERN, "");
            }
            super.print(s);
        }
        
        @Override
        public void println(String s) {
            if (s != null) {
                s = s.replaceAll(ANSI_ESCAPE_PATTERN, "");
            }
            super.println(s);
        }
    }
    
    public static void main(String[] args) {
        // Replace System.out with our custom PrintStream
        System.setOut(new NoAnsiPrintStream(System.out));
        
        showLogo();
        greet();
        
        Scanner scanner = new Scanner(System.in);
        String userInput = ""; // Initialize to empty string
        
        // Main input loop
        do {
            try {
                System.out.print("> ");
                userInput = scanner.nextLine().trim();
                
                if (userInput.trim().isEmpty()) {
                    throw new DukeException(EMPTY_INPUT);
                }
                
                String[] parts = userInput.split("\\s+", 2);
                String commandStr = parts[0].toLowerCase();
                String arguments = parts.length > 1 ? parts[1] : "";
                
                CommandType command = CommandType.fromString(commandStr);
                if (command == null) {
                    throw new DukeException(UNKNOWN_COMMAND);
                }
                
                switch (command) {
                case LIST:
                    listTasks();
                    break;
                case MARK:
                    markTask(arguments, true);
                    break;
                case UNMARK:
                    markTask(arguments, false);
                    break;
                case TODO:
                    addTodo(arguments);
                    break;
                case DEADLINE:
                    addDeadline(arguments);
                    break;
                case EVENT:
                    addEvent(arguments);
                    break;
                case DELETE:
                    deleteTask(arguments);
                    break;
                case BYE:
                    // Will exit the loop
                    break;
                default:
                    throw new task.DukeException(UNKNOWN_COMMAND);
                }
            } catch (task.DukeException e) {
                showError(e.getMessage());
            } catch (Exception e) {
                showError("An unexpected error occurred: " + e.getMessage());
            }
        } while (!userInput.equalsIgnoreCase("bye"));
        
        farewell();
        scanner.close();
    }
    
    
    private static void showError(String message) {
        System.out.println("     " + message);
    }
    
    /**
     * Lists all tasks currently being tracked.
     */
    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("     Your list of tasks is empty, like a stage before the play begins.");
            return;
        }
        
        System.out.println("     " + LIST_HEADER);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
    }
    
    /**
     * Marks a task as done or not done.
     * 
     * @param args The task number to mark
     * @param isDone Whether to mark as done (true) or not done (false)
     * @throws DukeException If the task number is invalid
     */
    private static void markTask(String args, boolean isDone) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(args.trim()) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new DukeException("No such task exists. The number must be between 1 and " + tasks.size());
            }
            
            Task task = tasks.get(taskIndex);
            if (isDone) {
                task.mark();
                System.out.println("     " + MARK_OK);
            } else {
                task.unmark();
                System.out.println("     " + UNMARK_OK);
            }
            System.out.println("       " + task);
        } catch (NumberFormatException e) {
            throw new DukeException("The task number must be a valid number.");
        }
    }
    
    /**
     * Adds a new todo task.
     * 
     * @param description The description of the todo
     * @throws DukeException If the description is empty
     */
    private static void addTodo(String description) throws DukeException {
        if (description.trim().isEmpty()) {
            throw new DukeException(TODO_EMPTY);
        }
        
        Task newTask = new Todo(description);
        tasks.add(newTask);
        printTaskAdded(newTask);
    }
    
    /**
     * Adds a new deadline task.
     * 
     * @param args The description and deadline in the format "description /by when"
     * @throws DukeException If the format is invalid
     */
    private static void addDeadline(String args) throws DukeException {
        String[] parts = args.split("\\s+/by\\s+", 2);
        if (parts.length < 2) {
            throw new DukeException(DEADLINE_NEEDS_BY);
        }
        
        String description = parts[0].trim();
        String by = parts[1].trim();
        
        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException(DEADLINE_EMPTY);
        }
        
        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        printTaskAdded(newTask);
    }
    
    /**
     * Adds a new event task.
     * 
     * @param args The description and time range in the format "description /from start /to end"
     * @throws DukeException If the format is invalid
     */
    private static void addEvent(String args) throws DukeException {
        String[] parts = args.split("\\s+/from\\s+", 2);
        if (parts.length < 2) {
            throw new DukeException(EVENT_NEEDS_FROM_TO);
        }
        
        String description = parts[0].trim();
        String[] timeParts = parts[1].split("\\s+/to\\s+", 2);
        
        if (timeParts.length < 2) {
            throw new DukeException(EVENT_NEEDS_FROM_TO);
        }
        
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new DukeException("An event requires a description, start time, and end time.");
        }
        
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        printTaskAdded(newTask);
    }
    
    /**
     * Deletes a task from the list.
     * 
     * @param args The task number to delete
     * @throws DukeException If the task number is invalid
     */
    private static void deleteTask(String args) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(args.trim()) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new DukeException("No such task exists. The number must be between 1 and " + tasks.size());
            }
            
            Task removedTask = tasks.remove(taskIndex);
            System.out.println(DIVIDER);
            System.out.println("     Very well. I've removed this task from the records:");
            System.out.println("       " + removedTask);
            System.out.println("     Now you have " + tasks.size() + " task" + (tasks.size() != 1 ? "s" : "") + " in the list.");
            System.out.print(DIVIDER);
        } catch (NumberFormatException e) {
            throw new DukeException("The task number must be a valid number.");
        }
    }
    
    /**
     * Prints a message when a task is added.
     * 
     * @param task The task that was added
     */
    private static void printTaskAdded(Task task) {
        System.out.println(DIVIDER);
        System.out.println("     " + ADDED_HEADER);
        System.out.println("       " + task);
        System.out.println("     Now you have " + tasks.size() + " task" + (tasks.size() != 1 ? "s" : "") + " in the list.");
        System.out.print(DIVIDER);
    }
    
    /**
     * Displays V's farewell message.
     */
    private static void farewell() {
        System.out.println("\n" + DIVIDER);
        System.out.println("     The curtain descends, everything ends too soon, too soon.");
        System.out.println("     Beneath this mask there is more than flesh. Beneath this mask there is an idea.");
        System.out.println("     And ideas are bulletproof!");
        System.out.println("     Farewell. May we meet again in the shadows.");
        System.out.print(DIVIDER);
    }
    
    /**
     * Displays the V logo.
     */
    private static void showLogo() {
        System.out.println(LOGO);
    }
    
    /**
     * Displays V's theatrical greeting.
     */
    private static void greet() {
        System.out.println("Voilà! In view, a voice of the vox populi.");
        System.out.println();
        System.out.println("Behold—the visage behind the letter.");
        System.out.println();
        System.out.println("A humble vessel of verbosity and vigilance.");
        System.out.println();
        System.out.println("I am V. Voice for the voiceless. What do you require?");
        System.out.println();
    }
}
