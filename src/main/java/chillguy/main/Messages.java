package chillguy.main;

import chillguy.ui.Ui;

/**
 * This class contains constant message strings used throughout the ChillGuy chatbot application.
 * <p>
 * These messages are used to display various status updates, errors, and prompts to the user during
 * the interaction with the chatbot. Each constant represents a different user-facing message that the
 * chatbot can display in different scenarios.
 */
public class Messages {
    public static final String MESSAGE_HELLO = "Mmm... were you looking for me?\n"
                                               + Ui.LINE_PREFIX + "I'm just a chill guy.";
    public static final String MESSAGE_LOAD = "Chill for while...\n"
                                              + Ui.LINE_PREFIX + "I'm trying to recall your tasks from last time.";
    public static final String MESSAGE_BYE = "Bye...\n"
                                             + Ui.LINE_PREFIX + "hope to see you and chill together again soon...";
    public static final String MESSAGE_CALL = "Anyways, you can call me if you need any help,\n"
                                              + Ui.LINE_PREFIX + "or, let's just chill as usual..";;
    public static final String MESSAGE_HELP = "I'm the best at chilling,\n"
                                              + Ui.LINE_PREFIX + "but I can do these too...";
    public static final String MESSAGE_TRY_AGAIN = "I'm sorry...\n"
                                                   + Ui.LINE_PREFIX + "Do you mind chilling on your own for while\n"
                                                   + Ui.LINE_PREFIX + "then trying again?";
    public static final String MESSAGE_ADD_START = "Gotcha... I've added this task :";
    public static final String MESSAGE_ADD_END = "But remember, you can always chill first.";
    public static final String MESSAGE_LOAD_TASKS_START = "Alright, here's what you have :";
    public static final String MESSAGE_SHOW_TASKS_START = "Seems like you were too chill\n"
                                                          + Ui.LINE_PREFIX + "and forgot what you had...\n"
                                                          + Ui.LINE_PREFIX + "Anyways, here's what you have :";
    public static final String MESSAGE_FIND_START = "Seems like you were too chill\n"
                                                    + Ui.LINE_PREFIX + "and forgot what you had...\n"
                                                    + Ui.LINE_PREFIX + "Anyways, here are the matching ones :";
    public static final String MESSAGE_NO_TASKS = "Looks like you have nothing in the list...\n"
                                                  + Ui.LINE_PREFIX + "Cool, let's just sit back and chill.";
    public static final String MESSAGE_MARK_START = "Nice... I've marked this task as done :";
    public static final String MESSAGE_MARK_END = "Think you can chill a bit now.";
    public static final String MESSAGE_UNMARK_START = "Ok... I've marked this task as not done yet :";
    public static final String MESSAGE_UNMARK_END = "But no worries, you are always welcome to just chill.";
    public static final String MESSAGE_DELETE_START = "Noted... I've removed this task :";
    public static final String MESSAGE_DELETE_END = "You know what, I also thought\n"
                                                    + Ui.LINE_PREFIX + "we didn't really need that";
}
