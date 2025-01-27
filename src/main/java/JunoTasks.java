import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public JunoTask makeTask(JunoTaskType type, String description, boolean isDone) throws JunoException, DateTimeParseException {
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
            DateTimeFormatter inputFormatter;
            if (desc[1].trim().contains(" ")) {
                inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                try {
                    curr = new JunoDeadline(desc[0].trim(), isDone, LocalDateTime.parse(desc[1].trim(), inputFormatter));
                } catch (DateTimeParseException e) {
                    throw new JunoException(DEADLINE_ERROR, true);
                }
            } else {
                inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                try {
                    curr = new JunoDeadline(desc[0].trim(), isDone, LocalDate.parse(desc[1].trim(), inputFormatter));
                } catch (DateTimeParseException e) {
                    throw new JunoException(DEADLINE_ERROR, true);
                }
            }
        } else {
            if (description.isEmpty()) {
                throw new JunoException(EVENT_ERROR, false);
            } else if (!description.contains("/from") || !description.contains("/to") ) {
                throw new JunoException(EVENT_ERROR, true);
            }
            String[] desc = description.split("/from", 2);
            String[] fromTo = desc[1].split("/to", 2);
            DateTimeFormatter inputFormatter;
            if (fromTo[0].trim().contains(" ")) {
                inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                try {
                    curr = new JunoEvent(desc[0].trim(), isDone, LocalDateTime.parse(fromTo[0].trim(), inputFormatter),
                            LocalDateTime.parse(fromTo[1].trim(), inputFormatter));
                } catch (DateTimeParseException e) {
                    throw new JunoException(EVENT_ERROR, true);
                }
            } else {
                inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                try {
                    curr = new JunoEvent(desc[0].trim(), isDone, LocalDate.parse(fromTo[0].trim(), inputFormatter),
                            LocalDate.parse(fromTo[1].trim(), inputFormatter));
                } catch (DateTimeParseException e) {
                    throw new JunoException(EVENT_ERROR, true);
                }
            }
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

    public Map<Integer, JunoTask> getTasks() throws JunoException {
        if (this.taskNum == 0) {
            throw new JunoException(LIST_ERROR);
        }

        return this.taskList;
    }

    public List<JunoTask> getTasksOnDate(LocalDate date) throws JunoException {
        List<JunoTask> tasksOnDate = new ArrayList<>();

        for (JunoTask task : this.taskList.values()) {
            if (task instanceof JunoDeadline deadline) {
                if (deadline.getByDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof JunoEvent event) {
                if (event.getFromDate().equals(date) || event.getToDate().equals(date)) {
                    tasksOnDate.add(task);
                }
            }
        }

        if (tasksOnDate.isEmpty()) {
            throw new JunoException(LIST_WITH_DATE_ERROR);
        }

        return tasksOnDate;
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