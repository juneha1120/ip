import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import enums.JunoTaskType;

import static enums.JunoErrorType.*;
import static enums.JunoTaskType.*;

public class JunoTasks {
    private Map<Integer, JunoTask> taskList;
    private int taskNum;

    private final String storagePath = "./data/juno.txt";

    public JunoTasks() {
        this.taskList = new LinkedHashMap<>();
        this.taskNum = 0;
    }

    public int getTaskNum() {
        return this.taskNum;
    }

    public void addTask(JunoTask task) throws IOException {
        this.taskList.put(++this.taskNum, task);
        this.saveTasks();
    }

    public JunoTask makeTask(JunoTaskType type, String description) throws JunoException {
        return this.makeTask(type, description, false);
    }

    public JunoTask makeTask(JunoTaskType type, String description, boolean isDone) throws JunoException {
        JunoTask curr;
        if (type.equals(TODO)) {
            if (description.isEmpty()) {
                throw new JunoException(TODO_ERROR);
            }
            curr = new JunoTodo(description, isDone);
        } else if (type.equals(DEADLINE)) {
            if (description.isEmpty()) {
                throw new JunoException(DEADLINE_ERROR, false);
            } else if (!description.contains("/by")) {
                throw new JunoException(DEADLINE_ERROR, true);
            }
            String[] desc = description.split("/by", 2);
            curr = new JunoDeadline(desc[0].trim(), isDone, desc[1].trim());
        } else {
            if (description.isEmpty()) {
                throw new JunoException(EVENT_ERROR, false);
            } else if (!description.contains("/from") || !description.contains("/to") ) {
                throw new JunoException(EVENT_ERROR, true);
            }
            String[] desc = description.split("/from", 2);
            String[] fromTo = desc[1].split("/to", 2);
            curr = new JunoEvent(desc[0].trim(), isDone, fromTo[0].trim(), fromTo[1].trim());
        }

        return curr;
    }

    public JunoTask deleteTask(String command) throws JunoException, IOException {
        JunoTask curr;
        int currNum;
        try {
            currNum = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new JunoException(DELETE_ERROR, command);
        }

        if (this.taskList.containsKey(currNum)) {
            curr = this.taskList.remove(currNum);
            this.renumberTasks();
        } else {
            throw new JunoException(DELETE_ERROR, command);
        }

        return curr;
    }

    public void renumberTasks() throws IOException {
        Map<Integer, JunoTask> newTaskList = new LinkedHashMap<>();
        int newTaskNum = 0;

        for (Map.Entry<Integer, JunoTask> entry : this.taskList.entrySet()) {
            newTaskList.put(++newTaskNum, entry.getValue());
        }

        this.taskList = newTaskList;
        this.taskNum = newTaskNum;
        this.saveTasks();
    }

    public JunoTask markTask(String command) throws JunoException, IOException {
        int currNum;
        try {
            currNum = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new JunoException(MARK_ERROR, false, command);
        }

        JunoTask curr = this.taskList.get(currNum);
        if (curr == null) {
            throw new JunoException(MARK_ERROR, false, command);
        } else if (curr.isDone()) {
            throw new JunoException(MARK_ERROR, true, command);
        }

        curr.mark();
        this.saveTasks();
        return curr;
    }

    public JunoTask unmarkTask(String command) throws JunoException, IOException {
        int currNum;
        try {
            currNum = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new JunoException(UNMARK_ERROR, true, command);
        }

        JunoTask curr = this.taskList.get(currNum);
        if (curr == null) {
            throw new JunoException(UNMARK_ERROR, true, command);
        } else if (!curr.isDone()) {
            throw new JunoException(UNMARK_ERROR, false, command);
        }

        curr.unmark();
        this.saveTasks();
        return curr;
    }

    public void showTasks() throws JunoException {
        if (this.taskNum == 0) {
            throw new JunoException(LIST_ERROR);
        }

        System.out.println(" Here's what you have :");
        this.taskList.forEach((taskNum, task) ->
                System.out.println("  " + taskNum + ". " + task.toString()));
    }

    public void saveTasks() throws IOException {
        StringBuilder data = new StringBuilder();
        for (Map.Entry<Integer, JunoTask> entry : this.taskList.entrySet()) {
            data.append(entry.getValue().toFileFormat()).append("\n");
        }

        Path path = Paths.get(storagePath);
        Files.createDirectories(path.getParent());
        Files.write(path, data.toString().getBytes());
    }

    public void loadTasks() throws IOException {
        Map<Integer, JunoTask> loadedList = new LinkedHashMap<>();
        Path path = Paths.get(storagePath);

        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            this.taskList = loadedList;
        }

        List<String> lines = Files.readAllLines(path);
        int loadedNum = 0;

        for (String line : lines) {
            try {
                JunoTask task = JunoTask.fromFileFormat(line);
                if (task != null) {
                    loadedList.put(++loadedNum, task);
                }
            } catch (JunoException e) {
                System.out.println();
                System.out.println(e.getMessage());
            }
        }

        this.taskList = loadedList;
        this.taskNum = loadedNum;
    }
}