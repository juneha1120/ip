import java.util.Scanner;

import static enums.JunoTaskType.*;

public class Juno {
    public static void main(String[] args) {
        JunoUI ui = new JunoUI();
        ui.showGreetingMessage();
        ui.loadTasks();

        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            // Read user input
            input = scanner.nextLine().trim();
            String[] command = input.split(" ", 2);
            if (command.length < 2) {
                command = new String[] {command[0], ""};
            }

            // Main Logic
            if (command[0].equalsIgnoreCase("bye")) {
                ui.showExitMessage(); break;
            } else if (command[0].equalsIgnoreCase("juno")) {
                ui.showHelp();
            } else if (command[0].equalsIgnoreCase("todo")) {
                ui.addTask(TODO, command[1]);
            } else if (command[0].equalsIgnoreCase("deadline")) {
                ui.addTask(DEADLINE, command[1]);
            } else if (command[0].equalsIgnoreCase("event")) {
                ui.addTask(EVENT, command[1]);
            } else if (command[0].equalsIgnoreCase("list")) {
                if (!command[1].isEmpty()) {
                    ui.showTasksOnDate(command[1]);
                } else {
                    ui.showTasks();
                }
            } else if (command[0].equalsIgnoreCase("mark")) {
                ui.markTask(command[1]);
            } else if (command[0].equalsIgnoreCase("unmark")) {
                ui.unmarkTask(command[1]);
            } else if (command[0].equalsIgnoreCase("delete")) {
                ui.deleteTask(command[1]);
            } else {
                ui.showAgainMessage();
            }
        }

        scanner.close();
    }
}