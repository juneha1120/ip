package juno.storage;

import juno.exceptions.JunoException;

import juno.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static juno.enums.ErrorType.*;

public class Storage {
    private final String filePath;
    public static final String EXAMPLE = "./data/juno.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static Task fromFileFormat(String line) throws JunoException {
        String[] parts = line.split(" \\| ");
        if (line.isEmpty()) {
            return null;
        } else if (parts.length < 3) {
            throw new JunoException(INVALID_FORMAT_ERROR, line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T" :
                return new Todo(description, isDone);
            case "D" :
                String by = parts[3];
                if (by.contains("T")) {
                    return new Deadline(description, isDone, LocalDateTime.parse(by));
                } else {
                    return new Deadline(description, isDone, LocalDate.parse(by));
                }
            case "E" :
                String from = parts[3];
                String to = parts[4];
                if (from.contains("T")) {
                    return new Event(description, isDone, LocalDateTime.parse(from),
                            LocalDateTime.parse(to));
                } else {
                    return new Event(description, isDone, LocalDate.parse(from),
                            LocalDate.parse(to));
                }
            default :
                throw new JunoException(INVALID_TYPE_ERROR, line);
        }
    }

    public void saveTasks(TaskList taskList) throws JunoException {
        StringBuilder data = new StringBuilder();
        for (Map.Entry<Integer, Task> entry : taskList.getTaskList().entrySet()) {
            data.append(entry.getValue().toFileFormat()).append("\n");
        }

        Path path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, data.toString().getBytes());
        } catch (IOException e) {
            throw new JunoException(CREATE_FILE_ERROR);
        }
    }

    public TaskList loadTasks() throws JunoException {
        Map<Integer, Task> loadedTaskList = new LinkedHashMap<>();
        int loadedTaskCount = 0;

        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (IOException e) {
                throw new JunoException(CREATE_FILE_ERROR);
            }
        }

        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                Task task = fromFileFormat(line);
                if (task != null) {
                    loadedTaskList.put(++loadedTaskCount, task);
                }
            }
        } catch (IOException e) {
            throw new JunoException(READ_FILE_ERROR);
        }

        return new TaskList(loadedTaskList);
    }
}
