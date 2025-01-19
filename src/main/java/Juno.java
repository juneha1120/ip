import java.util.*;

public class Juno {
    public static void main(String[] args) {
        // Print greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Juno.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            // Read user input
            userInput = scanner.nextLine();

            // Print exiting message when user input is exit command
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // Echo user input
            System.out.println("____________________________________________________________");
            System.out.println(" " + userInput);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
