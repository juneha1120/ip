import java.util.*;

public class JunoUI {
    private final Map<String, String> commands;
    private final Map<Integer, JunoTask> tasks;
    private int taskNum = 0;

    public JunoUI() {
        commands = new LinkedHashMap<>();
        initCommands();
        tasks = new LinkedHashMap<>();
        taskNum++;
    }

    // Initialise lists of commands
    public void initCommands() {
        // Add commands and their descriptions
        commands.put("1. bye (or exit)", "exits the chatbot.");
        commands.put("2. juno (or help)", "shows available commands.");
        commands.put("3. todo + (task description)", "adds task without any date/time to task list");
        commands.put("4. deadline + (task description) + /by (date/time)", "adds task due specific date/time to task list");
        commands.put("5. event + (task description) + /from (date/time) + /to (date/time)", "adds task from and to specific date/time to task list");
        commands.put("6. list (or tasks)", "shows list of added tasks.");
        commands.put("7. mark + (task number)", "marks specific task number as done");
        commands.put("8. unmark + (task number)", "unmarks specific task number");
    }

    // Add task to tasks
    public void addTask(String taskType, String description) {
        JunoTask curr = this.makeTask(taskType, description);
        if (curr == null) return;

        tasks.put(taskNum, curr);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task :");
        System.out.println("  " + curr.toString());
        System.out.println(" Now you have " + taskNum + " tasks in the list.");
        System.out.println("____________________________________________________________");
        taskNum++;
    }

    // Make task according to its type
    public JunoTask makeTask(String taskType, String description) {
        JunoTask curr;
        if (taskType.equals("todo")) {
            curr = new JunoTodo(description);
        } else if (taskType.equals("deadline")) {
            if (!description.contains("/by")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Looks like you did not follow :");
                System.out.println("  deadline + (description) + /by (date/time)");
                System.out.println("____________________________________________________________");
                return null;
            }
            String[] desc = description.split("/by", 2);
            curr = new JunoDeadline(desc[0].trim(), desc[1].trim());
        } else {
            if (!description.contains("/from") || !description.contains("/to") ) {
                System.out.println("____________________________________________________________");
                System.out.println(" Looks like you did not follow :");
                System.out.println("  event + (description) + /from (date/time) + /to (date/time)");
                System.out.println("____________________________________________________________");
                return null;
            }
            String[] desc = description.split("/from", 2);
            String[] fromTo = desc[1].split("/to", 2);
            curr = new JunoEvent(desc[0].trim(), fromTo[0].trim(), fromTo[1].trim());
        }
        return curr;
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
                System.out.println(" " + curr.toString());
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
                System.out.println(" " + curr.toString());
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
            tasks.forEach((taskNum, task) -> System.out.println("  " + taskNum + ". " + task.toString()));
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
        System.out.println("     _");
        System.out.println("    | |_   _ _____  ____");
        System.out.println(" _  | | | | |  _  \\/ _  \\");
        System.out.println("| |_| | |_| | | | | |_| |");
        System.out.println(" \\____|\\____|_| |_|_____/");
        System.out.println();
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
        commands.forEach((command, description) -> System.out.println("  " + command + " : " + description));
        System.out.println("____________________________________________________________");
    }

    // Echo user input
    public void echoInput(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + input);
        System.out.println("____________________________________________________________");
    }
}
