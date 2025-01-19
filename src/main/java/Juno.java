import java.util.*;

public class Juno {
    public static void main(String[] args) {
        JunoUI ui = new JunoUI();
        Scanner scanner = new Scanner(System.in);
        String input;

        ui.showGreetingMessage();
        while (true) {
            // Read user input
            input = scanner.nextLine().trim();
            String[] command = input.split(" ", 2);

            if (command[0].equalsIgnoreCase("bye") || command[0].equalsIgnoreCase("exit")) {ui.showExitMessage(); break;}
            else if (command[0].equalsIgnoreCase("juno") || command[0].equalsIgnoreCase("help")) ui.showHelp();
            else if (command[0].equalsIgnoreCase("list") || command[0].equalsIgnoreCase("tasks")) ui.showTasks();
            else if (command[0].equalsIgnoreCase("mark") && command.length > 1) ui.markTask(command[1]);
            else if (command[0].equalsIgnoreCase("unmark") && command.length > 1) ui.unmarkTask(command[1]);
            else if (command[0].equalsIgnoreCase("add") && command.length > 1) ui.addTask(command[1]);
            else ui.echoInput(input);
        }

        scanner.close();
    }
}
