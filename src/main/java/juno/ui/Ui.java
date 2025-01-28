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

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public String readCommand() {
        String fullInputLine = scanner.nextLine().trim();

        while (this.shouldIgnore(fullInputLine)) {
            fullInputLine = scanner.nextLine();
        }

        return fullInputLine;
    }

    public void showToUser(String... messages) {
        for (String message : messages) {
            if (message.equals(DIVIDER)) {
                System.out.println(message);
            } else {
                System.out.println(LINE_PREFIX + message);
            }
        }
    }

    public void showDivider() {
        this.showToUser(DIVIDER);
    }

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

    public void showLoadingMessage(TaskList taskList) {
        this.showToUser(
                DIVIDER,
                MESSAGE_LOAD,
                EMPTY_DIVIDER);
        this.showTasks(taskList);
        this.showToUser(
                DIVIDER);
    }

    public void showHelp() {
        this.showToUser(
                MESSAGE_HELP,
                EMPTY_DIVIDER);
        this.showToUser(COMMAND_DESCRIPTION_LIST);
    }

    public void showAdd(Task task, int taskCount) {
        this.showToUser(
                MESSAGE_ADD_START,
                LINE_PREFIX + task.toString(),
                "Now you have " + taskCount + " tasks in the list.");
    }

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

    public void showTasksWithDate(TaskList taskList, LocalDate date) {
        this.showToUser(
                MESSAGE_SHOW_TASKS_START);
        this.showToUser(taskList.getStringTaskList());
        this.showToUser("You have " + taskList.getTaskCount() + " tasks on "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    public void showMark(Task task) {
        this.showToUser(
                MESSAGE_MARK_START,
                LINE_PREFIX + task.toString());
    }

    public void showUnmark(Task task) {
        this.showToUser(
                MESSAGE_UNMARK_START,
                LINE_PREFIX + task.toString());
    }

    public void showDelete(Task task, int taskCount) {
        this.showToUser(
                MESSAGE_DELETE_START,
                LINE_PREFIX + task.toString(),
                "Now you have " + taskCount + " tasks in the list.");
    }

    public void showExitMessage() {
        this.showToUser(
                MESSAGE_BYE,
                EMPTY_DIVIDER);
        this.showToUser(
                JUNO_LOGO);
    }

    public void showTryAgainMessage() {
        this.showToUser(
                MESSAGE_TRY_AGAIN);
    }

    public void showError(String message) {
        this.showToUser(
                message);
    }
}
