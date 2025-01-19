import java.util.*;

public class Juno {
    public static void main(String[] args) {
        JunoUI ui = new JunoUI();
        Scanner scanner = new Scanner(System.in);
        String input;

        ui.showGreetingMessage();
        while (true) {
            // Read user input
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {ui.showExitMessage(); break;}
            else if (input.equalsIgnoreCase("help") || input.equalsIgnoreCase("juno")) ui.showHelp();
            else ui.echoInput(input);
        }

        scanner.close();
    }
}
