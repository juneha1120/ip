import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

import enums.JunoTaskType;

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
        commands.put("# bye", "exits the chatbot.");
        commands.put("# juno", "shows available commands.");
        commands.put("# todo <description>",
                "\n     adds task without any date/time to task list.");
        commands.put("# deadline <description> /by <date/time>",
                "\n     adds task due specific date/time to task list.");
        commands.put("# event <description> /from <start> /to <end>",
                "\n     adds task from and to specific date/time to task list.");
        commands.put("# list", "shows list of added tasks.");
        commands.put("# mark <task number>", "marks specified task as done.");
        commands.put("# unmark <task number>", "unmarks specified task number.");
        commands.put("# delete <task number>", "delete specified task.");
    }

    // Add task to tasks
    public void addTask(JunoTaskType type, String description) {
        try {
            System.out.println("___________________________________________________________________________");
            JunoTask curr = this.tasks.makeTask(type, description);
            this.tasks.addTask(curr);
            System.out.println(" Got it. I've added this task :");
            System.out.println("  " + curr.toString());
            System.out.println(" Now you have " + this.tasks.getTaskNum() + " tasks in the list.");
        } catch (JunoException | IOException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Please follow this format : list <d/M/yyyy>.");
        } finally {
            System.out.println("___________________________________________________________________________");
        }
    }

    // Add task to tasks
    public void deleteTask(String command) {
        try {
            System.out.println("___________________________________________________________________________");
            JunoTask curr = this.tasks.deleteTask(command);
            System.out.println(" Noted. I've removed this task :");
            System.out.println("  " + curr.toString());
            System.out.println(" Now you have " + this.tasks.getTaskNum() + " tasks in the list.");
        } catch (JunoException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("___________________________________________________________________________");
        }
    }

    // Mark task as done
    public void markTask(String command) {
        try {
            System.out.println("___________________________________________________________________________");
            JunoTask curr = this.tasks.markTask(command);
            System.out.println(" Nice! I've marked this task as done :");
            System.out.println("  " + curr.toString());
        } catch (JunoException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("___________________________________________________________________________");
        }
    }

    // Unmark task
    public void unmarkTask(String command) {
        try {
            System.out.println("___________________________________________________________________________");
            JunoTask curr = this.tasks.unmarkTask(command);
            System.out.println(" Ok, I've marked this task as not done yet :");
            System.out.println("  " + curr.toString());
        } catch (JunoException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("___________________________________________________________________________");
        }
    }

    // Print tasks
    public void showTasks() {
        try {
            System.out.println("___________________________________________________________________________");
            Map<Integer, JunoTask> taskList = this.tasks.getTasks();
            System.out.println(" Here's what you have :");
            taskList.forEach((taskNum, task) -> System.out.println("  " + taskNum + ". " + task));
            System.out.println(" You have " + this.tasks.getTaskNum() + " tasks in the list.");
        } catch (JunoException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("___________________________________________________________________________");
        }
    }

    // Print tasks on specified date
    public void showTasksOnDate(String command) {
        try {
            System.out.println("___________________________________________________________________________");
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate date = LocalDate.parse(command, inputFormatter);
            List<JunoTask> tasksOnDate = this.tasks.getTasksOnDate(date);
            System.out.println(" Here's what you have :");
            int countOnDate = 0;
            for (JunoTask task : tasksOnDate) {
                System.out.println("  " + task);
                countOnDate++;
            }
            System.out.println(" You have " + countOnDate + " tasks on the date.");
        } catch (DateTimeParseException e) {
            System.out.println("  Please follow this format : list <d/M/yyyy>.");
        } catch (JunoException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("___________________________________________________________________________");
        }
    }

    // Load tasks
    public void loadTasks() {
        try {
            System.out.println(" I'm retrieving your saved task list from last time...");
            this.tasks.loadTasks();
            System.out.println();
            Map<Integer, JunoTask> taskList = this.tasks.getTasks();
            taskList.forEach((taskNum, task) -> System.out.println("  " + taskNum + ". " + task));
            System.out.println(" You have " + this.tasks.getTaskNum() + " tasks in the list.");
        } catch (IOException e) {
            System.out.println("  Oops! Ran into some issues while loading tasks : " + e.getMessage());
        } catch (JunoException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("___________________________________________________________________________");
        }
    }

    // Print greeting message
    public void showGreetingMessage() {
        System.out.println("___________________________________________________________________________");
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
        System.out.println("___________________________________________________________________________");
    }

    // Print exiting message
    public void showExitMessage() {
        System.out.println("___________________________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("___________________________________________________________________________");
    }

    // Print the list of available commands
    public void showHelp() {
        System.out.println("___________________________________________________________________________");
        System.out.println(" You called? Here's what I can do :");
        commands.forEach((command, description)
                -> System.out.println("  " + command + " : " + description));
        System.out.println("___________________________________________________________________________");
    }

    // Print try-again message
    public void showAgainMessage() {
        System.out.println("___________________________________________________________________________");
        System.out.println(" I'm sorry. Could you try again?");
        System.out.println("___________________________________________________________________________");
    }
}