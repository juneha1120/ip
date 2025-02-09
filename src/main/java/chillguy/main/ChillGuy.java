package chillguy.main;

import chillguy.commands.Command;
import chillguy.commands.ExitCommand;
import chillguy.exceptions.ChillGuyException;
import chillguy.exceptions.ChillGuyTestException;
import chillguy.parser.Parser;
import chillguy.storage.Storage;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * The {@code ChillGuy} class is the main entry point of the task management application.
 * It handles user interactions, parses commands, and manages the task list. It also interacts
 * with the {@link Storage} class to load and save tasks, and uses {@link Parser} to interpret user inputs.
 * The class is responsible for displaying messages via the {@link TextUi} class and processing commands
 * such as adding, deleting, or marking tasks.
 */
public class ChillGuy {
    private final Parser parser;
    private final Storage storage;
    private final TextUi textUi;
    private GraphicalUi graphicalUi;
    private TaskList tasks;

    /**
     * Constructs a {@code ChillGuy} instance with the specified file path.
     * Initializes the necessary components: {@link Parser}, {@link TaskList}, {@link Storage}, and {@link TextUi}.
     *
     * @param filePath The file path for loading and saving tasks.
     */
    public ChillGuy(String filePath) {
        assert filePath != null && !filePath.isEmpty() : "File path cannot be null or empty";

        this.parser = new Parser();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);
        this.textUi = new TextUi();
    }

    /**
     * Constructs a {@code ChillGuy} instance with the pre-defined file path.
     * Initializes the necessary components: {@link Parser}, {@link TaskList}, {@link Storage}, {@link TextUi}, and
     * {@link GraphicalUi}.
     */
    public ChillGuy() {
        this("./data/chillguy.txt");
        this.graphicalUi = new GraphicalUi();
    }

    /**
     * The main entry point for the {@code ChillGuy} application. Initializes the application and calls
     * the {@link #runWithTUi()} method to begin processing user commands.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new ChillGuy("./data/chillguy.txt").runWithTUi();
    }

    /**
     * Starts the {@code ChillGuy} application. Displays the greeting message and loads the existing task list.
     * It then enters a loop where it continuously accepts user commands until the exit command is issued.
     */
    public void runWithTUi() {
        this.textUi.showGreetingMessage();

        assert this.tasks != null : "Task list should be initialized properly";

        try {
            this.tasks = this.storage.loadTasks();
            textUi.showLoadingMessage(this.tasks);
        } catch (ChillGuyException e) {
            textUi.showError(e.getMessage());
        }

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.textUi.readCommand();
                Command c = this.parser.parse(fullCommand);
                this.textUi.showDivider();
                c.execute(this.tasks, this.storage, this.textUi);
                isExit = ExitCommand.isExit(c);
                this.textUi.showDivider();
            } catch (ChillGuyTestException ignored) {
                // Ignored
            } catch (ChillGuyException e) {
                this.textUi.showError(e.getMessage());
                this.textUi.showDivider();
            }
        }
    }

    /**
     * Starts the {@code ChillGuy} application with {@link GraphicalUi}.
     *
     * @return A {@link String} response to be handled.
     */
    public String startWithGUi() {
        assert this.graphicalUi != null : "GraphicalUi should be initialized before use";

        this.graphicalUi.initResponse();
        this.graphicalUi.appendGreetingMessage();
        try {
            this.tasks = this.storage.loadTasks();
            this.graphicalUi.appendLoadingMessage(this.tasks);
        } catch (ChillGuyException e) {
            this.graphicalUi.appendError(e.getMessage());
        }

        return this.graphicalUi.getResponse();
    }

    /**
     * Runs the {@code ChillGuy} application with {@link GraphicalUi} and {@link String} input.
     *
     * @param fullInput The full user input entered.
     * @return A {@link String} response to be handled.
     */
    public String getResponseWithGUi(String fullInput) {
        assert this.graphicalUi != null : "GraphicalUi should be initialized before use";

        this.graphicalUi.initResponse();
        try {
            Command c = this.parser.parse(fullInput);
            c.execute(this.tasks, this.storage, this.graphicalUi);
        } catch (ChillGuyTestException ignored) {
            // Ignored
        } catch (ChillGuyException e) {
            this.graphicalUi.appendError(e.getMessage());
        }

        return this.graphicalUi.getResponse();
    }
}
