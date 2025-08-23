import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * V - A personal assistant with a theatrical flair, inspired by V for Vendetta.
 * This program greets the user, manages tasks, and bids them farewell.
 */
public class V {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    
    // Command constants
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_BYE = "bye";
    
    // Delimiters
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
                String command = parts[0].toLowerCase();
                String arguments = parts.length > 1 ? parts[1] : "";
                
                switch (command) {
                case CMD_LIST:
                    listTasks();
                    break;
                case CMD_MARK:
                    markTask(arguments, true);
                    break;
                case CMD_UNMARK:
                    markTask(arguments, false);
                    break;
                case CMD_TODO:
                    addTodo(arguments);
                    break;
                case CMD_DEADLINE:
                    addDeadline(arguments);
                    break;
                case CMD_EVENT:
                    addEvent(arguments);
                    break;
                case CMD_BYE:
                    // Will exit the loop
                    break;
                default:
                    throw new DukeException(UNKNOWN_COMMAND);
                }
            } catch (DukeException e) {
                showError(e.getMessage());
            } catch (Exception e) {
                showError("An unexpected error occurred: " + e.getMessage());
            }
        } while (!userInput.equalsIgnoreCase(CMD_BYE));
        
        farewell();
        scanner.close();
    }
    
    
    private static void showError(String message) {
        System.out.println("     " + message);
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
    
    /**
     * Displays all tasks with dramatic flair.
     */
    private static void listTasks() {
        System.out.println("     " + LIST_HEADER);
        if (taskCount == 0) {
            System.out.println("     The stage is bare, the script unwritten. No tasks yet to perform.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("     %d.%s%n", i + 1, tasks[i]);
            }
        }
        System.out.print(DIVIDER);
    }
    
    /**
     * Adds a new task to the task list with dramatic flair.
     * 
     * @param description The task description to be added
     */
    private static void addTodo(String description) throws DukeException {
        if (description.trim().isEmpty()) {
            throw new DukeException(TODO_EMPTY);
        }
        
        if (taskCount >= MAX_TASKS) {
            throw new DukeException("My apologies, but your list of tasks has reached its limit.");
        }
        
        tasks[taskCount] = new Todo(description);
        taskCount++;
        printTaskAdded(tasks[taskCount-1]);
    }
    
    private static void addDeadline(String arguments) throws DukeException {
        arguments = arguments.trim();
        if (arguments.isEmpty()) {
            throw new DukeException(DEADLINE_EMPTY);
        }
        
        if (!arguments.contains(DELIMITER_BY)) {
            throw new DukeException(DEADLINE_NEEDS_BY);
        }
        
        String[] parts = arguments.split("\\s*" + DELIMITER_BY + "\\s*", 2);
        String description = parts[0].trim();
        String by = parts.length > 1 ? parts[1].trim() : "";
        
        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException(DEADLINE_EMPTY);
        }
        
        if (taskCount >= MAX_TASKS) {
            throw new DukeException("My apologies, but your list of tasks has reached its limit.");
        }
        
        tasks[taskCount] = new Deadline(description, by);
        taskCount++;
        printTaskAdded(tasks[taskCount-1]);
    }
    
    /**
     * Adds a new event task with start and end times.
     * 
     * @param arguments The event details including description, /from and /to times
     * @throws DukeException If the input format is invalid
     */
    private static void addEvent(String arguments) throws DukeException {
        arguments = arguments.trim();
        if (arguments.isEmpty()) {
            throw new DukeException("An event requires a description and time range.");
        }
        
        if (!arguments.contains(DELIMITER_FROM) || !arguments.contains(DELIMITER_TO)) {
            throw new DukeException(EVENT_NEEDS_FROM_TO);
        }
        
        // Split into description and time parts
        String[] firstSplit = arguments.split("\\s*" + DELIMITER_FROM + "\\s*", 2);
        if (firstSplit.length < 2) {
            throw new DukeException("Event must include both start and end times.");
        }
        
        String description = firstSplit[0].trim();
        String[] timeParts = firstSplit[1].split("\\s*" + DELIMITER_TO + "\\s*", 2);
        
        if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new DukeException("Both start and end times must be provided.");
        }
        
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        
        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        
        if (taskCount >= MAX_TASKS) {
            throw new DukeException("My apologies, but your list of tasks has reached its limit.");
        }
        
        tasks[taskCount] = new Event(description, from, to);
        taskCount++;
        printTaskAdded(tasks[taskCount-1]);
    }
    /**
     * Marks a task as done or not done based on the user's command.
     * 
     * @param argument The user's mark/unmark command (e.g., "mark 1" or "unmark 1")
     * @param isDone Whether to mark as done (true) or not done (false)
     */
    private static void markTask(String argument, boolean isDone) throws DukeException {
        if (argument.trim().isEmpty()) {
            throw new DukeException("Which task shall I mark? A number, if you please.");
        }
        
        try {
            int index = Integer.parseInt(argument.trim()) - 1;
            if (index < 0 || index >= taskCount) {
                throw new DukeException(String.format("Task %d remains unwritten in our grand narrative.", index + 1));
            }
            
            if (isDone) {
                tasks[index].mark();
            } else {
                tasks[index].unmark();
            }
            
            System.out.println("     " + (isDone ? MARK_OK : UNMARK_OK));
            System.out.println("       " + tasks[index]);
        } catch (NumberFormatException e) {
            throw new DukeException("Numbers, please. Try: " + (isDone ? "mark" : "unmark") + " 2");
        }
    }
    
    /**
     * Displays V's theatrical farewell message.
     */
    private static void farewell() {
        System.out.println("The curtain descends, everything ends too soon, too soon.");
        System.out.println("Beneath this mask there is more than flesh. Beneath this mask there is an idea.");
        System.out.println("And ideas are bulletproof!");
        System.out.println("Farewell. May we meet again in the shadows.");
    }
    
    /**
     * Prints the task addition message with proper formatting.
     * 
     * @param task The task that was added
     */
    private static void printTaskAdded(Task task) {
        System.out.println("     " + ADDED_HEADER);
        System.out.println("       " + task);
        System.out.printf("     The ledger now holds %d task%s.%n", 
                taskCount, taskCount == 1 ? "" : "s");
    }
    
}
