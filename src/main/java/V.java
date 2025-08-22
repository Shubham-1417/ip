import java.util.Scanner;

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

    /**
     * The main entry point of the V program.
     * Displays the V logo, greets the user, processes user input in a loop,
     * and bids farewell when the user is done.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        showLogo();
        greet();
        
        Scanner scanner = new Scanner(System.in);
        String userInput;
        
        // Main input loop
        do {
            System.out.print("> ");
            userInput = scanner.nextLine().trim();
            
            String[] parts = userInput.split("\\s+", 2);
            String command = parts[0].toLowerCase();
            String arguments = parts.length > 1 ? parts[1] : "";
            
            switch (command) {
            case CMD_LIST:
                listTasks();
                break;
            case CMD_MARK:
                markTask(arguments);
                break;
            case CMD_UNMARK:
                unmarkTask(arguments);
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
                System.out.println("My dear friend, I'm afraid I don't understand that command.");
                System.out.println("Try 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', or 'bye'.");
            }
        } while (!userInput.equalsIgnoreCase(CMD_BYE));
        
        farewell();
        scanner.close();
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
        System.out.println("Voilà! In view, a humble voice of the vox populi.");
        System.out.println("A humble vessel of verbosity and vigilance.");
        System.out.println(" I am V.");
        System.out.println(" What can I do for you?");
    }
    
    /**
     * Displays all tasks with dramatic flair.
     */
    private static void listTasks() {
        System.out.println(DIVIDER + "     Behold, your list of tasks:");
        if (taskCount == 0) {
            System.out.println("     You have no tasks, my friend. How... peaceful.\n" + DIVIDER);
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("     %d.%s\n", i + 1, tasks[i]);
            }
            System.out.print(DIVIDER);
        }
    }
    
    /**
     * Adds a new task to the task list with dramatic flair.
     * 
     * @param description The task description to be added
     */
    private static void addTodo(String description) {
        if (description.trim().isEmpty()) {
            System.out.println(DIVIDER + "     My dear friend, even a simple 'todo' requires a description.\n" + DIVIDER);
            return;
        }
        
        if (taskCount >= MAX_TASKS) {
            System.out.println(DIVIDER + "     My apologies, but your list of tasks has reached its limit.\n" + DIVIDER);
            return;
        }
        
        tasks[taskCount] = new Todo(description);
        taskCount++;
        
        printTaskAdded(tasks[taskCount-1]);
    }
    
    private static void addDeadline(String arguments) {
        if (!arguments.contains(DELIMITER_BY)) {
            System.out.println(DIVIDER + "     A deadline requires both a description and a '/by' time.\n" + DIVIDER);
            return;
        }
        
        String[] parts = arguments.split("\\s" + DELIMITER_BY + "\\s*", 2);
        String description = parts[0].trim();
        String by = parts.length > 1 ? parts[1].trim() : "";
        
        if (description.isEmpty() || by.isEmpty()) {
            System.out.println(DIVIDER + "     Both description and deadline time are required.\n" + DIVIDER);
            return;
        }
        
        if (taskCount >= MAX_TASKS) {
            System.out.println(DIVIDER + "     My apologies, but your list of tasks has reached its limit.\n" + DIVIDER);
            return;
        }
        
        tasks[taskCount] = new Deadline(description, by);
        taskCount++;
        
        printTaskAdded(tasks[taskCount-1]);
    }
    
    private static void addEvent(String arguments) {
        if (!arguments.contains(DELIMITER_FROM) || !arguments.contains(DELIMITER_TO)) {
            System.out.println(DIVIDER + "     An event requires '/from' and '/to' times.\n" + DIVIDER);
            return;
        }
        
        String[] parts = arguments.split("\\s" + DELIMITER_FROM + "\\s*", 2);
        String description = parts[0].trim();
        
        if (parts.length < 2) {
            System.out.println(DIVIDER + "     Please specify both start and end times.\n" + DIVIDER);
            return;
        }
        
        String[] timeParts = parts[1].split("\\s" + DELIMITER_TO + "\\s*", 2);
        String from = timeParts[0].trim();
        String to = timeParts.length > 1 ? timeParts[1].trim() : "";
        
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            System.out.println(DIVIDER + "     Description, start time, and end time are all required.\n" + DIVIDER);
            return;
        }
        
        if (taskCount >= MAX_TASKS) {
            System.out.println(DIVIDER + "     My apologies, but your list of tasks has reached its limit.\n" + DIVIDER);
            return;
        }
        
        tasks[taskCount] = new Event(description, from, to);
        taskCount++;
        
        printTaskAdded(tasks[taskCount-1]);
    }
    
    private static void printTaskAdded(Task task) {
        System.out.println(DIVIDER + "     Got it. I've added this task:");
        System.out.println("       " + task);
        System.out.printf("     Now you have %d task%s in the list.\n" + DIVIDER, 
                taskCount, taskCount == 1 ? "" : "s");
    }
    
    /**
     * Marks a task as done based on the user's command.
     * 
     * @param command The user's mark command (e.g., "mark 1")
     */
    private static void markTask(String argument) {
        try {
            int taskNum = Integer.parseInt(argument.trim()) - 1;
            if (taskNum >= 0 && taskNum < taskCount) {
                tasks[taskNum].mark();
                System.out.println(DIVIDER + "     A task fulfilled! Marked as done:");
                System.out.println("       " + tasks[taskNum] + "\n" + DIVIDER);
            } else {
                System.out.println(DIVIDER + "     My dear friend, that task number is but an illusion.\n" + DIVIDER);
            }
        } catch (NumberFormatException e) {
            System.out.println(DIVIDER + "     Pray, tell me the number of the task you wish to mark.\n" + DIVIDER);
        }
    }
    
    /**
     * Marks a task as not done based on the user's command.
     * 
     * @param command The user's unmark command (e.g., "unmark 1")
     */
    private static void unmarkTask(String argument) {
        try {
            int taskNum = Integer.parseInt(argument.trim()) - 1;
            if (taskNum >= 0 && taskNum < taskCount) {
                tasks[taskNum].unmark();
                System.out.println(DIVIDER + "     Very well, I've marked this task as not done:");
                System.out.println("       " + tasks[taskNum] + "\n" + DIVIDER);
            } else {
                System.out.println(DIVIDER + "     My dear friend, that task number is but an illusion.\n" + DIVIDER);
            }
        } catch (NumberFormatException e) {
            System.out.println(DIVIDER + "     Pray, tell me the number of the task you wish to unmark.");
            System.out.println("     (e.g., 'unmark 2' to mark task 2 as not done)\n" + DIVIDER);
        }
    }
    
    /**
     * Displays V's theatrical farewell message.
     */
    private static void farewell() {
        System.out.println("\n" + DIVIDER +
            "    The curtain falls on our performance today.\n" +
            "    Until we meet again, remember: ideas are bulletproof.\n" +
            "    Farewell, my friend.\n" +
            DIVIDER);
    }
}
