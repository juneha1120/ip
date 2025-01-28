package juno.ui;

import static juno.commands.CommandList.COMMAND_DESCRIPTION_LIST;
import static juno.main.Messages.MESSAGE_ADD_START;
import static juno.main.Messages.MESSAGE_BYE;
import static juno.main.Messages.MESSAGE_CALL;
import static juno.main.Messages.MESSAGE_DELETE_START;
import static juno.main.Messages.MESSAGE_HELLO;
import static juno.main.Messages.MESSAGE_HELP;
import static juno.main.Messages.MESSAGE_LOAD;
import static juno.main.Messages.MESSAGE_MARK_START;
import static juno.main.Messages.MESSAGE_NO_TASKS;
import static juno.main.Messages.MESSAGE_SHOW_TASKS_START;
import static juno.main.Messages.MESSAGE_TRY_AGAIN;
import static juno.main.Messages.MESSAGE_UNMARK_START;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import juno.task.Task;
import juno.task.TaskList;

/**
 * The {@code Ui} class handles user interactions for the Juno application.
 * It provides methods for displaying messages, task lists, and other relevant
 * information to the user.
 * <p>
 * It also provides functionality for reading user input and controlling the flow
 * of the user interface.
 */
public class Ui {
    public static final String DIVIDER = "========================================================================";
    public static final String EMPTY_DIVIDER = "";
    public static final String LINE_PREFIX = " ";
    public static final String[] JUNO_LOGO = {
        "      _",
        "     | |_   _ _____  ____",
        "  _  | | | | |  _  \\/ _  \\",
        " | |_| | |_| | | | | |_| |",
        "  \\____|\\____|_| |_|_____/"
    };

    private final Scanner scanner;

    /**
     * Constructs a {@code Ui} object that handles user input and output.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Checks if the input line should be ignored (i.e., is empty or whitespace).
     *
     * @param rawInputLine The user input to check.
     * @return {@code true} if the input line should be ignored, {@code false} otherwise.
     */
    public boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Reads a command from the user input.
     * The input is trimmed and empty lines are ignored.
     *
     * @return The full command entered by the user.
     */
    public String readCommand() {
        String fullInputLine = scanner.nextLine().trim();

        while (this.shouldIgnore(fullInputLine)) {
            fullInputLine = scanner.nextLine();
        }

        return fullInputLine;
    }

    /**
     * Displays the provided messages to the user, with the appropriate formatting.
     *
     * @param messages The messages to display.
     */
    public void showToUser(String... messages) {
        for (String message : messages) {
            if (message.equals(DIVIDER)) {
                System.out.println(message);
            } else {
                System.out.println(LINE_PREFIX + message);
            }
        }
    }

    /**
     * Displays a divider line to the user.
     */
    public void showDivider() {
        this.showToUser(DIVIDER);
    }

    /**
     * Displays a greeting message along with the Juno logo.
     */
    public void showGreetingMessage() {
        this.showToUser(
                DIVIDER);
        this.showToUser(
                JUNO_LOGO);
        this.showToUser(
                EMPTY_DIVIDER,
                MESSAGE_HELLO,
                EMPTY_DIVIDER,
                MESSAGE_CALL,
                DIVIDER);
    }

    /**
     * Displays the loading message and the current task list.
     *
     * @param taskList The current list of tasks.
     */
    public void showLoadingMessage(TaskList taskList) {
        this.showToUser(
                DIVIDER,
                MESSAGE_LOAD,
                EMPTY_DIVIDER);
        this.showTasks(taskList);
        this.showToUser(
                DIVIDER);
    }

    /**
     * Displays the help message with a description of available commands.
     */
    public void showHelp() {
        this.showToUser(
                MESSAGE_HELP,
                EMPTY_DIVIDER);
        this.showToUser(COMMAND_DESCRIPTION_LIST);
    }

    /**
     * Displays a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks after addition.
     */
    public void showAdd(Task task, int taskCount) {
        this.showToUser(
                MESSAGE_ADD_START,
                LINE_PREFIX + task.toString(),
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the list of all tasks to the user.
     *
     * @param taskList The current list of tasks.
     */
    public void showTasks(TaskList taskList) {
        if (taskList.getTaskCount() == 0) {
            this.showToUser(MESSAGE_NO_TASKS);
        } else {
            this.showToUser(
                    MESSAGE_SHOW_TASKS_START);
            this.showToUser(taskList.getStringTaskList());
            this.showToUser(
                    "You have " + taskList.getTaskCount() + " tasks in the list.");
        }
    }

    /**
     * Displays the list of tasks that are scheduled for a specific date.
     *
     * @param taskList The current list of tasks.
     * @param date The specific date to filter tasks by.
     */
    public void showTasksWithDate(TaskList taskList, LocalDate date) {
        this.showToUser(
                MESSAGE_SHOW_TASKS_START);
        this.showToUser(taskList.getStringTaskList());
        this.showToUser("You have " + taskList.getTaskCount() + " tasks on "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Displays a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked.
     */
    public void showMark(Task task) {
        this.showToUser(
                MESSAGE_MARK_START,
                LINE_PREFIX + task.toString());
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmark(Task task) {
        this.showToUser(
                MESSAGE_UNMARK_START,
                LINE_PREFIX + task.toString());
    }

    /**
     * Displays a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks after deletion.
     */
    public void showDelete(Task task, int taskCount) {
        this.showToUser(
                MESSAGE_DELETE_START,
                LINE_PREFIX + task.toString(),
                "Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays the exit message to the user.
     */
    public void showExitMessage() {
        this.showToUser(
                MESSAGE_BYE,
                EMPTY_DIVIDER);
        this.showToUser(
                JUNO_LOGO);
    }

    /**
     * Displays a message asking the user to try again in case of an error.
     */
    public void showTryAgainMessage() {
        this.showToUser(
                MESSAGE_TRY_AGAIN);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        this.showToUser(
                message);
    }
}
