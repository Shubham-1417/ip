import java.util.Scanner;

/**
 * V - A personal assistant with a theatrical flair, inspired by V for Vendetta.
 * This program greets the user, manages tasks, and bids them farewell.
 */
public class V {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
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
            
            if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else if (userInput.toLowerCase().startsWith("mark ")) {
                markTask(userInput);
            } else if (userInput.toLowerCase().startsWith("unmark ")) {
                unmarkTask(userInput);
            } else if (!userInput.equalsIgnoreCase("bye")) {
                addTask(userInput);
            }
        } while (!userInput.equalsIgnoreCase("bye"));
        
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
     * Adds a new task to the task list with dramatic flair.
     * 
     * @param task The task to be added
     */
    /**
     * Adds a new task to the task list with dramatic flair.
     * 
     * @param description The task description to be added
     */
    private static void addTask(String description) {
        if (taskCount >= MAX_TASKS) {
            System.out.println(DIVIDER +
                "    My apologies, but my memory is at capacity.\n" +
                "    I can remember no more tasks. (Maximum " + MAX_TASKS + " tasks reached)\n" +
                DIVIDER);
            return;
        }
        
        Task newTask = new Task(description);
        tasks[taskCount] = newTask;
        taskCount++;
        
        System.out.println(DIVIDER +
            "    'Tis done! This task I've committed to memory:\n" +
            "      " + newTask + "\n" +
            "    Now you have " + taskCount + " task" + (taskCount == 1 ? "" : "s") + " in the list.\n" +
            DIVIDER);
    }
    
    /**
     * Displays all tasks with dramatic flair.
     */
    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println(DIVIDER +
                "    My apologies, but your list of tasks is as empty as a deserted stage.\n" +
                "    Pray, give me something to remember!\n" +
                DIVIDER);
            return;
        }
        
        StringBuilder sb = new StringBuilder(DIVIDER);
        sb.append("    Behold! Your tasks, in all their glory:\n");
        
        for (int i = 0; i < taskCount; i++) {
            sb.append("    " + (i + 1) + ". " + tasks[i].toString() + "\n");
        }
        
        sb.append(DIVIDER);
        System.out.print(sb.toString());
    }
    
    /**
     * Displays V's theatrical farewell message.
     */
    /**
     * Marks a task as done based on the user's command.
     * 
     * @param command The user's mark command (e.g., "mark 1")
     */
    private static void markTask(String command) {
        try {
            int taskNum = Integer.parseInt(command.substring(5).trim());
            if (taskNum < 1 || taskNum > taskCount) {
                System.out.println(DIVIDER +
                    "    My apologies, but I cannot mark what does not exist.\n" +
                    "    Task " + taskNum + " is beyond the veil of my memory.\n" +
                    DIVIDER);
                return;
            }
            
            Task task = tasks[taskNum - 1];
            task.mark();
            
            System.out.println(DIVIDER +
                "    A deed well done! I've marked this task as complete:\n" +
                "      " + task + "\n" +
                DIVIDER);
                
        } catch (NumberFormatException e) {
            System.out.println(DIVIDER +
                "    Pray, speak clearly! The task number must be a number.\n" +
                "    (e.g., 'mark 2' to mark task 2 as done)\n" +
                DIVIDER);
        }
    }
    
    /**
     * Marks a task as not done based on the user's command.
     * 
     * @param command The user's unmark command (e.g., "unmark 1")
     */
    private static void unmarkTask(String command) {
        try {
            int taskNum = Integer.parseInt(command.substring(7).trim());
            if (taskNum < 1 || taskNum > taskCount) {
                System.out.println(DIVIDER +
                    "    My apologies, but I cannot unmark what does not exist.\n" +
                    "    Task " + taskNum + " remains beyond my reach.\n" +
                    DIVIDER);
                return;
            }
            
            Task task = tasks[taskNum - 1];
            task.unmark();
            
            System.out.println(DIVIDER +
                "    Very well, I've marked this task as not done.\n" +
                "    The wheel turns, and we begin again.\n" +
                "      " + task + "\n" +
                DIVIDER);
                
        } catch (NumberFormatException e) {
            System.out.println(DIVIDER +
                "    Pray, speak clearly! The task number must be a number.\n" +
                "    (e.g., 'unmark 2' to mark task 2 as not done)\n" +
                DIVIDER);
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
