package chillguy.commands;

import static chillguy.enums.ErrorType.LIST_WITH_KEYWORD_ERROR;
import static chillguy.enums.ErrorType.NO_KEYWORD_ERROR;

import java.util.LinkedHashMap;
import java.util.Map;

import chillguy.exceptions.ChillGuyException;
import chillguy.storage.Storage;
import chillguy.task.Task;
import chillguy.task.TaskList;
import chillguy.ui.GraphicalUi;
import chillguy.ui.TextUi;

/**
 * The {@code FindCommand} class handles the execution of the "find" command,
 * which allows users to search for tasks containing a specific keyword in their names.
 * <p>
 * It retrieves a list of tasks that match the specified keyword and displays them to the user.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : shows list of tasks with specified keyword.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " study CS2103T";
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword The keyword to search for in task names.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Retrieves the list of tasks from the {@code taskList} that contain the specified keyword.
     * <p>
     * If no tasks match the keyword or if the keyword is empty, a {@code ChillGuyException} will be thrown.
     *
     * @param taskList The list of tasks to search through.
     * @return A {@link TaskList} containing the tasks that match the keyword.
     * @throws ChillGuyException If no tasks match the keyword or if the keyword is empty.
     */
    public TaskList getTasksWithKeyword(TaskList taskList) throws ChillGuyException {
        Map<Integer, Task> taskListOriginal = taskList.getTaskList();
        Map<Integer, Task> taskListWithKeyword = new LinkedHashMap<>();
        int taskCount = 0;

        for (Task task : taskListOriginal.values()) {
            if (task.getTaskName().toLowerCase().contains(this.keyword.toLowerCase())) {
                taskListWithKeyword.put(++taskCount, task);
            }
        }

        if (taskListWithKeyword.isEmpty()) {
            throw new ChillGuyException(LIST_WITH_KEYWORD_ERROR);
        }

        if (keyword.isEmpty()) {
            throw new ChillGuyException(NO_KEYWORD_ERROR);
        }

        return new TaskList(taskListWithKeyword);
    }


    /**
     * Executes the find command, displaying the tasks that contain the specified keyword.
     *
     * @param taskList The list of tasks to search through.
     * @param storage The storage object for reading and writing task data.
     * @param textUi The UI object for displaying the result to the user.
     * @throws ChillGuyException If there is an error during the find operation.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, TextUi textUi) throws ChillGuyException {
        TaskList taskListWithKeyword = this.getTasksWithKeyword(taskList);
        textUi.showFind(taskListWithKeyword, this.keyword);
    }

    /**
     * Executes the find command, displaying the tasks that contain the specified keyword.
     *
     * @param taskList The list of tasks to search through.
     * @param storage The storage object for reading and writing task data.
     * @param graphicalUi The UI object for returning the result to the user.
     * @throws ChillGuyException If there is an error during the find operation.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, GraphicalUi graphicalUi) throws ChillGuyException {
        TaskList taskListWithKeyword = this.getTasksWithKeyword(taskList);
        graphicalUi.appendFind(taskListWithKeyword, this.keyword);
    }
}
