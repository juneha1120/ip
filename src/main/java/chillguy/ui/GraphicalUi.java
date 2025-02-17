package chillguy.ui;

import static chillguy.commands.CommandList.COMMAND_LIST;
import static chillguy.main.Messages.MESSAGE_ADD_END;
import static chillguy.main.Messages.MESSAGE_ADD_START;
import static chillguy.main.Messages.MESSAGE_BYE;
import static chillguy.main.Messages.MESSAGE_CALL;
import static chillguy.main.Messages.MESSAGE_DELETE_END;
import static chillguy.main.Messages.MESSAGE_DELETE_START;
import static chillguy.main.Messages.MESSAGE_FIND_START;
import static chillguy.main.Messages.MESSAGE_HELLO;
import static chillguy.main.Messages.MESSAGE_HELP_LIST_END;
import static chillguy.main.Messages.MESSAGE_HELP_LIST_START;
import static chillguy.main.Messages.MESSAGE_HELP_SINGLE_COMMAND;
import static chillguy.main.Messages.MESSAGE_LOAD;
import static chillguy.main.Messages.MESSAGE_LOAD_TASKS_START;
import static chillguy.main.Messages.MESSAGE_MARK_END;
import static chillguy.main.Messages.MESSAGE_MARK_START;
import static chillguy.main.Messages.MESSAGE_NO_TASKS;
import static chillguy.main.Messages.MESSAGE_REMINDER_START;
import static chillguy.main.Messages.MESSAGE_SHOW_TASKS_START;
import static chillguy.main.Messages.MESSAGE_TRY_AGAIN;
import static chillguy.main.Messages.MESSAGE_UNMARK_END;
import static chillguy.main.Messages.MESSAGE_UNMARK_START;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chillguy.enums.TaskType;
import chillguy.task.Task;
import chillguy.task.TaskList;

/**
 * The {@code TextUi} class handles user interactions for the ChillGuy application.
 * It provides methods for displaying messages, task lists, and other relevant
 * information to the user.
 * <p>
 * It also provides functionality for reading user input and controlling the flow
 * of the user interface.
 */
public class GraphicalUi {
    public static final String EMPTY_DIVIDER = "";
    private List<String> responseList;

    /**
     * Initializes the response list as a new empty {@code ArrayList}.
     */
    public void initResponse() {
        this.responseList = new ArrayList<>();
    }

    /**
     * Appends one or more response messages to the response list.
     *
     * @param messages the response messages to be added
     */
    public void appendResponse(String... messages) {
        this.responseList.addAll(Arrays.asList(messages));
    }

    /**
     * Retrieves all stored responses as a single formatted string,
     * where each response is separated by a newline character.
     *
     * @return a concatenated string of all responses, separated by newlines
     */
    public String getResponse() {
        String[] responseArr = this.responseList.toArray(new String[0]);
        return String.join("\n", responseArr);
    }

    /**
     * Returns a greeting message along with the ChillGuy logo.
     */
    public void respondWithGreetingMessage() {
        this.appendResponse(
                MESSAGE_HELLO,
                EMPTY_DIVIDER,
                MESSAGE_CALL);
    }

    /**
     * Returns the loading message and the current task list.
     *
     * @param taskList The current list of tasks.
     */
    public void respondWithLoadingMessage(TaskList taskList) {
        this.appendResponse(
                EMPTY_DIVIDER,
                MESSAGE_LOAD,
                EMPTY_DIVIDER);
        if (taskList.getTaskCount() == 0) {
            this.appendResponse(MESSAGE_NO_TASKS);
        } else {
            this.appendResponse(
                    MESSAGE_LOAD_TASKS_START);
            this.appendResponse(taskList.getStringTaskList());
            this.appendResponse(
                    "You have " + taskList.getTaskCount() + " task(s) in the list.");
        }
    }

    /**
     * Returns the help message of specified command description.
     */
    public void respondWithHelpMessage(String commandDescription) {
        this.appendResponse(
                MESSAGE_HELP_SINGLE_COMMAND,
                EMPTY_DIVIDER);
        this.appendResponse(commandDescription);
    }

    /**
     * Returns the list of available commands
     */
    public void respondWithCommandList() {
        this.appendResponse(
                MESSAGE_HELP_LIST_START,
                EMPTY_DIVIDER);
        this.appendResponse(COMMAND_LIST);
        this.appendResponse(
                EMPTY_DIVIDER,
                MESSAGE_HELP_LIST_END);
    }

    /**
     * Returns a message indicating that a task has been added to the list.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks after addition.
     */
    public void respondWithAddMessage(Task task, int taskCount) {
        this.appendResponse(
                MESSAGE_ADD_START,
                task.toString(),
                "Now you have " + taskCount + " task(s) in the list.",
                MESSAGE_ADD_END);
    }

    /**
     * Returns the list of all tasks to the user.
     *
     * @param taskList The current list of tasks.
     */
    public void respondWithTasks(TaskList taskList) {
        if (taskList.getTaskCount() == 0) {
            this.appendResponse(MESSAGE_NO_TASKS);
        } else {
            this.appendResponse(
                    MESSAGE_SHOW_TASKS_START);
            this.appendResponse(taskList.getStringTaskList());
            this.appendResponse(
                    "You have " + taskList.getTaskCount() + " task(s) in the list.");
        }
    }

    /**
     * Returns the list of tasks that are scheduled for a specific date.
     *
     * @param taskList The current list of tasks.
     * @param date The specific date to filter tasks by.
     */
    public void respondWithTasksOnDate(TaskList taskList, LocalDate date) {
        this.appendResponse(
                MESSAGE_SHOW_TASKS_START);
        this.appendResponse(taskList.getStringTaskList());
        this.appendResponse("You have " + taskList.getTaskCount() + " task(s) on "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ".");
    }

    /**
     * Returns the list of tasks that contains a specific keyword.
     *
     * @param taskList The current list of tasks.
     * @param keyword The specific keyword to filter tasks by.
     */
    public void respondWithFindMessage(TaskList taskList, String keyword) {
        this.appendResponse(
                MESSAGE_FIND_START);
        this.appendResponse(taskList.getStringTaskList());
        this.appendResponse(
                "You have " + taskList.getTaskCount() + " task(s) with keyword : " + keyword + ".");
    }

    /**
     * Returns the reminders of the specific task type.
     *
     * @param taskList The current list of tasks.
     * @param type The specific task type to filter tasks by.
     */
    public void respondWithReminders(TaskList taskList, TaskType type) {
        this.appendResponse(
                MESSAGE_REMINDER_START);
        this.appendResponse(taskList.getStringTaskList());
        this.appendResponse(
                "You have " + taskList.getTaskCount() + " reminder(s) with " + type + ".");
    }

    /**
     * Returns a message indicating that a task has been marked as completed.
     *
     * @param task The task that was marked.
     */
    public void respondWithMarkMessage(Task task) {
        this.appendResponse(
                MESSAGE_MARK_START,
                task.toString(),
                MESSAGE_MARK_END);
    }

    /**
     * Returns a message indicating that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void respondWithUnmarkMessage(Task task) {
        this.appendResponse(
                MESSAGE_UNMARK_START,
                task.toString(),
                MESSAGE_UNMARK_END);
    }

    /**
     * Returns a message indicating that a task has been deleted from the list.
     *
     * @param task The task that was deleted.
     * @param taskCount The total number of tasks after deletion.
     */
    public void respondWithDeleteMessage(Task task, int taskCount) {
        this.appendResponse(
                MESSAGE_DELETE_START,
                task.toString(),
                "Now you have " + taskCount + " task(s) in the list.",
                MESSAGE_DELETE_END);
    }

    /**
     * Returns the exit message to the user.
     */
    public void respondWithExitMessage() {
        this.appendResponse(
                MESSAGE_BYE);
    }

    /**
     * Returns a message asking the user to try again in case of an error.
     */
    public void respondWithTryAgainMessage() {
        this.appendResponse(
                MESSAGE_TRY_AGAIN);
    }

    /**
     * Returns an error message to the user.
     *
     * @param message The error message to display.
     */
    public void respondWithErrorMessage(String message) {
        this.appendResponse(
                message);
    }
}
