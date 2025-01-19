import java.util.*;

public class JunoUI {
    private final Map<String, String> commands;
    private Map<Integer, JunoTask> tasks;
    private int taskNum = 0;

    public JunoUI() {
        commands = new LinkedHashMap<>();
        initCommands();
        tasks = new LinkedHashMap<>();
        taskNum++;
    }

    public void initCommands() {
        // Add commands and their descriptions
        commands.put("bye (or exit)", "exits the chatbot.");
        commands.put("juno (or help)", "shows available commands.");
        commands.put("list (or tasks)", "shows list of added tasks.");
    }

    // Adds task to tasks
    public void addTask(String input) {
        tasks.put(taskNum, new JunoTask(input));
        System.out.println("____________________________________________________________");
        System.out.println(" Added : " + taskNum + ". " + input);
        System.out.println("____________________________________________________________");
        taskNum++;
    }

    // Mark task as done
    public void markTask(String command) {
        try {
            int taskNum = Integer.parseInt(command);
            if (tasks.containsKey(taskNum)) {
                JunoTask curr = tasks.get(taskNum);
                curr.mark();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done :");
                System.out.println(" " + curr.getStatusIcon() + curr.getDescription());
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" I don't seem to find that task.");
                System.out.println("____________________________________________________________");
            }
        } catch (NumberFormatException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" I don't seem to find that task.");
            System.out.println("____________________________________________________________");
        }
    }

    // Unmark task
    public void unmarkTask(String command) {
        try {
            int taskNum = Integer.parseInt(command);
            if (tasks.containsKey(taskNum)) {
                JunoTask curr = tasks.get(taskNum);
                curr.unmark();
                System.out.println("____________________________________________________________");
                System.out.println(" Ok, I've marked this task as not done yet :");
                System.out.println(" " + curr.getStatusIcon() + curr.getDescription());
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" I don't seem to find that task.");
                System.out.println("____________________________________________________________");
            }
        } catch (NumberFormatException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" I don't seem to find that task.");
            System.out.println("____________________________________________________________");
        }
    }

    // Print tasks
    public void showTasks() {
        if (taskNum > 1) {
            System.out.println("____________________________________________________________");
            System.out.println(" Here's what you have :");
            tasks.forEach((taskNum, task) -> System.out.println(taskNum + ". " + task.getStatusIcon() + task.getDescription()));
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Looks like you have nothing! ");
            System.out.println("____________________________________________________________");
        }
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

    // Echo user input
    public void echoInput(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + input);
        System.out.println("____________________________________________________________");
    }
}
