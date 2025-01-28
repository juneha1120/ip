package juno.commands;

import static juno.enums.ErrorType.LIST_WITH_KEYWORD_ERROR;
import static juno.enums.ErrorType.NO_KEYWORD_ERROR;

import java.util.LinkedHashMap;
import java.util.Map;

import juno.exceptions.JunoException;
import juno.storage.Storage;
import juno.task.Task;
import juno.task.TaskList;
import juno.ui.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_DESCRIPTION = COMMAND_WORD + " : shows list of tasks with specified keyword.\n"
            + " ".repeat(COMMAND_WORD.length()) + EXAMPLE_PREFIX + COMMAND_WORD + " study CS2103T";
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public TaskList getTasksWithKeyword(TaskList taskList) throws JunoException {
        Map<Integer, Task> taskListOriginal = taskList.getTaskList();
        Map<Integer, Task> taskListWithKeyword = new LinkedHashMap<>();
        int taskCount = 0;

        for (Task task : taskListOriginal.values()) {
            if (task.getTaskName().toLowerCase().contains(this.keyword.toLowerCase())) {
                taskListWithKeyword.put(++taskCount, task);
            }
        }

        if (taskListWithKeyword.isEmpty()) {
            throw new JunoException(LIST_WITH_KEYWORD_ERROR);
        }

        if (keyword.isEmpty()) {
            throw new JunoException(NO_KEYWORD_ERROR);
        }

        return new TaskList(taskListWithKeyword);
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws JunoException {
        TaskList taskListWithKeyword = this.getTasksWithKeyword(taskList);
        ui.showFind(taskListWithKeyword, this.keyword);
    }
}
