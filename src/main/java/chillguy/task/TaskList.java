package chillguy.task;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents a list of {@link Task}s, where each task is identified by a unique task number.
 * Provides functionality to manage tasks, such as adding tasks, retrieving task lists, and updating task counts.
 */
public class TaskList {
    private Map<Integer, Task> taskList;
    private int taskCount;

    /**
     * Constructs an empty {@code TaskList}.
     * Initializes an empty task list using a {@link LinkedHashMap} to maintain the insertion order.
     */
    public TaskList() {
        this.taskList = new LinkedHashMap<>();
    }

    /**
     * Constructs a {@code TaskList} with the specified initial task list.
     *
     * @param taskList A map containing tasks, where the key is the task number, and the value is the {@link Task}.
     */
    public TaskList(Map<Integer, Task> taskList) {
        this.taskList = taskList;
        this.taskCount = taskList.size();
    }

    /**
     * Adds a task to the task list. The task is assigned the next available task number.
     * Updates the task count after adding the new task.
     *
     * @param task The {@link Task} to be added to the task list.
     */
    public void addToTaskList(Task task) {
        assert task != null : "Task cannot be null";
        this.taskList.put(this.getTaskCount() + 1, task);
        updateTaskCount();
    }

    /**
     * Retrieves the current task list as a map, where the key is the task number, and the value is the {@link Task}.
     *
     * @return A map representing the task list.
     */
    public Map<Integer, Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Sets a new task list by replacing the current task list with the specified map.
     *
     * @param taskList A map containing the new task list to set.
     */
    public void setTaskList(Map<Integer, Task> taskList) {
        assert taskList != null : "Task list cannot be null";
        this.taskList = taskList;
    }

    /**
     * Retrieves the current number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Updates the task count to reflect the current size of the task list.
     * This method is invoked after adding or removing tasks to ensure the count remains accurate.
     */
    public void updateTaskCount() {
        this.taskCount = this.taskList.size();
    }

    /**
     * Converts the task list into an array of strings, each string representing a task with its task number.
     *
     * @return An array of strings, where each string represents a task in the list.
     */
    public String[] getStringTaskList() {
        return this.taskList.entrySet().stream()
                .map(entry -> " " + entry.getKey() + ". " + entry.getValue())
                .toArray(String[]::new);
    }
}
