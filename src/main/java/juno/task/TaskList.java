package juno.task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskList {
    private Map<Integer, Task> taskList;
    private int taskCount;

    public TaskList() {
        this.taskList = new LinkedHashMap<>();
    }

    public TaskList(Map<Integer, Task> taskList) {
        this.taskList = taskList;
        this.taskCount = taskList.size();
    }

    public void addToTaskList(Task task) {
        this.taskList.put(this.getTaskCount() + 1, task);
        updateTaskCount();
    }

    public Map<Integer, Task> getTaskList() {
        return this.taskList;
    }

    public void setTaskList(Map<Integer, Task> taskList) {
        this.taskList = taskList;
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public void updateTaskCount() {
        this.taskCount = this.taskList.size();
    }

    public String[] getStringTaskList() {
        List<String> list = new ArrayList<>();
        this.taskList.forEach((taskNum, task) ->
                list.add(" " + taskNum + ". " + task));
        return list.toArray(new String[0]);
    }
}
