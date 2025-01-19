import java.util.*;

public class JunoUI {
    private final Map<String, String> commands;
    private Map<Integer, String> tasks;
    private int taskCount = 0;

    public JunoUI() {
        commands = new HashMap<>();
        initCommands();
        tasks = new HashMap<>();
        initTasks();
    }

    public void initCommands() {
        // Add commands and their descriptions
        commands.put("bye", "exits the chatbot.");
        commands.put("juno OR help", "shows available commands.");
        commands.put("list OR tasks", "shows list of added tasks.");
    }

    public void initTasks() {
        tasks = new HashMap<>();
        taskCount++;
    }

    // Adds task to tasks
    public void addTask(String input) {
        tasks.put(taskCount++, input);
        System.out.println("____________________________________________________________");
        System.out.println(" Added : " + input);
        System.out.println("____________________________________________________________");
    }

    // Print tasks
    public void showTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here's what you have :");
        tasks.forEach((num, task) -> System.out.println(num + ". " + task));
        System.out.println("____________________________________________________________");
    }

    // Print greeting message
    public void showGreetingMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Juno.");
        System.out.println(" What can I do for you?");
        System.out.println();
        System.out.println(" Call my name if you need help.");
        System.out.println("____________________________________________________________");
    }

    // Print exiting message
    public void showExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    // Print the list of available commands
    public void showHelp() {
        System.out.println("____________________________________________________________");
        System.out.println(" You called? Here's what I can do :");
        commands.forEach((command, description) -> System.out.println(" " + command + " : " + description));
        System.out.println("____________________________________________________________");
    }

//    // Echo user input
//    public void echoInput(String input) {
//        System.out.println("____________________________________________________________");
//        System.out.println(" " + input);
//        System.out.println("____________________________________________________________");
//    }


}
