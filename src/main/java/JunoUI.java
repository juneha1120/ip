import java.util.*;
import enums.JunoType;

public class JunoUI {
    private final Map<String, String> commands;
    private final JunoTasks tasks;

    public JunoUI() {
        this.commands = new LinkedHashMap<>();
        initCommands();
        this.tasks = new JunoTasks();
    }

    // Initialise lists of commands
    public void initCommands() {
        // Add commands and their descriptions
        commands.put("1. bye (or exit)", "exits the chatbot.");
        commands.put("2. juno (or help)", "shows available commands.");
        commands.put("3. todo + (description)",
                 "\n     adds task without any date/time to task list");
        commands.put("4. deadline + (description) + /by(date/time)",
                 "\n     adds task due specific date/time to task list");
        commands.put("5. event + (description) + /from(date/time) + /to(date/time)",
                 "\n     adds task from and to specific date/time to task list");
        commands.put("6. list (or tasks)", "shows list of added tasks.");
        commands.put("7. mark + (task number)", "marks specific task number as done");
        commands.put("8. unmark + (task number)", "unmarks specific task number");
    }

    // Add task to tasks
    public void addTask(JunoType type, String description) {
        try {
            System.out.println("_________________________________________________________________");
            JunoTask curr = this.tasks.makeTask(type, description);
            this.tasks.addTask(curr);
            System.out.println(" Got it. I've added this task :");
            System.out.println("  " + curr.toString());
            System.out.println(" Now you have " + this.tasks.getTaskNum() + " tasks in the list.");
        } catch (JunoException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("_________________________________________________________________");
        }
    }

    // Mark task as done
    public void markTask(String command) {
        try {
            System.out.println("_________________________________________________________________");
            JunoTask curr = this.tasks.markTask(command);
            System.out.println(" Nice! I've marked this task as done :");
            System.out.println(" " + curr.toString());
        } catch (JunoException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("_________________________________________________________________");
        }
    }

    // Unmark task
    public void unmarkTask(String command) {
        try {
            System.out.println("_________________________________________________________________");
            JunoTask curr = this.tasks.unmarkTask(command);
            System.out.println(" Ok, I've marked this task as not done yet :");
            System.out.println(" " + curr.toString());
        } catch (JunoException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("_________________________________________________________________");
        }
    }

    // Print tasks
    public void showTasks() {
        try {
            System.out.println("_________________________________________________________________");
            this.tasks.showTasks();
        } catch (JunoException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("_________________________________________________________________");
        }
    }

    // Print greeting message
    public void showGreetingMessage() {
        System.out.println("_________________________________________________________________");
        System.out.println("      _");
        System.out.println("     | |_   _ _____  ____");
        System.out.println("  _  | | | | |  _  \\/ _  \\");
        System.out.println(" | |_| | |_| | | | | |_| |");
        System.out.println("  \\____|\\____|_| |_|_____/");
        System.out.println();
        System.out.println(" Hello! I'm Juno.");
        System.out.println(" What can I do for you?");
        System.out.println();
        System.out.println(" Call my name if you need help.");
        System.out.println("_________________________________________________________________");
    }

    // Print exiting message
    public void showExitMessage() {
        System.out.println("_________________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("_________________________________________________________________");
    }

    // Print the list of available commands
    public void showHelp() {
        System.out.println("_________________________________________________________________");
        System.out.println(" You called? Here's what I can do :");
        commands.forEach((command, description) -> System.out.println("  " + command + " : " + description));
        System.out.println("_________________________________________________________________");
    }

    // Print try-again message
    public void showAgainMessage() {
        System.out.println("_________________________________________________________________");
        System.out.println(" I'm sorry. Could you try again?");
        System.out.println("_________________________________________________________________");
    }
}