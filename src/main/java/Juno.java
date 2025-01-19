import java.util.*;

public class Juno {
    public static void main(String[] args) {
        JunoUI ui = new JunoUI();
        Scanner scanner = new Scanner(System.in);
        String input;

        ui.greet();
        while (true) {
            // Read user input
            input = scanner.nextLine();

            // Exit if user input is "bye" (case-insensitive)
            if (input.equalsIgnoreCase("bye")) {
                ui.exit();
                break;
            }

            // Echo user input
            ui.echoInput(input);
        }

        scanner.close();
    }
}
