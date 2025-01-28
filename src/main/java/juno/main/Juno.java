package juno.main;

import juno.commands.Command;
import juno.commands.ExitCommand;
import juno.exceptions.JunoException;
import juno.exceptions.JunoTestException;
import juno.parser.Parser;
import juno.storage.Storage;
import juno.task.TaskList;
import juno.ui.Ui;

/**
 * The {@code Juno} class is the main entry point of the task management application.
 * It handles user interactions, parses commands, and manages the task list. It also interacts
 * with the {@link Storage} class to load and save tasks, and uses {@link Parser} to interpret user inputs.
 * The class is responsible for displaying messages via the {@link Ui} class and processing commands
 * such as adding, deleting, or marking tasks.
 */
public class Juno {
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a {@code Juno} instance with the specified file path.
     * Initializes the necessary components: {@link Parser}, {@link TaskList}, {@link Storage}, and {@link Ui}.
     *
     * @param filePath The file path for loading and saving tasks.
     */
    public Juno(String filePath) {
        this.parser = new Parser();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
        this.ui = new Ui();
    }

    /**
     * The main entry point for the {@code Juno} application. Initializes the application and calls
     * the {@link #run()} method to begin processing user commands.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new Juno("./data/juno.txt").run();
    }

    /**
     * Starts the {@code Juno} application. Displays the greeting message and loads the existing task list.
     * It then enters a loop where it continuously accepts user commands until the exit command is issued.
     */
    public void run() {
        this.ui.showGreetingMessage();
        try {
            this.tasks = this.storage.loadTasks();
            ui.showLoadingMessage(this.tasks);
        } catch (JunoException e) {
            ui.showError(e.getMessage());
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = this.parser.parse(fullCommand);
                this.ui.showDivider();
                c.execute(this.tasks, this.storage, this.ui);
                isExit = ExitCommand.isExit(c);
                this.ui.showDivider();
            } catch (JunoTestException ignored) {
                // Ignored
            } catch (JunoException e) {
                this.ui.showError(e.getMessage());
                this.ui.showDivider();
            }
        }
    }
}

