package juno.storage;

import static juno.enums.ErrorType.CREATE_FILE_ERROR;
import static juno.enums.ErrorType.INVALID_FORMAT_ERROR;
import static juno.enums.ErrorType.INVALID_TYPE_ERROR;
import static juno.enums.ErrorType.READ_FILE_ERROR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import juno.exceptions.JunoException;
import juno.task.Deadline;
import juno.task.Event;
import juno.task.Task;
import juno.task.TaskList;
import juno.task.Todo;

/**
 * The {@code Storage} class is responsible for managing task data storage. It provides methods
 * for saving tasks to a file and loading tasks from a file. It ensures that tasks are properly
 * serialized and deserialized into a format that can be written to or read from the file system.
 */
public class Storage {
    public static final String EXAMPLE = "./data/juno.txt";
    private final String filePath;

    /**
     * Constructs a {@code Storage} object that manages the file at the specified file path.
     *
     * @param filePath The path to the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts a line of text from the file into a {@link Task} object.
     * The line must be in the format "type | status | description | ...".
     * This method handles different types of tasks including {@link Todo}, {@link Deadline}, and {@link Event}.
     *
     * @param line The line of text from the file to be parsed.
     * @return A {@link Task} object created from the parsed line.
     * @throws JunoException If the line is in an invalid format or the task type is unknown.
     */
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
        case "T":
            return new Todo(description, isDone);
        case "D":
            String by = parts[3];
            if (by.contains("T")) {
                return new Deadline(description, isDone, LocalDateTime.parse(by));
            } else {
                return new Deadline(description, isDone, LocalDate.parse(by));
            }
        case "E":
            String from = parts[3];
            String to = parts[4];
            if (from.contains("T")) {
                return new Event(description, isDone, LocalDateTime.parse(from),
                        LocalDateTime.parse(to));
            } else {
                return new Event(description, isDone, LocalDate.parse(from),
                        LocalDate.parse(to));
            }
        default:
            throw new JunoException(INVALID_TYPE_ERROR, line);
        }
    }

    /**
     * Saves the tasks from a {@link TaskList} to a file.
     * The tasks are serialized into a format that can be saved to the file.
     * Each task is written as a line in the format "type | status | description | ...".
     *
     * @param taskList The {@link TaskList} containing the tasks to be saved.
     * @throws JunoException If an error occurs while creating the file or writing to it.
     */
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

    /**
     * Loads tasks from a file and returns them as a {@link TaskList}.
     * Each line in the file is parsed into a {@link Task} object.
     * If the file doesn't exist, a new file is created.
     *
     * @return A {@link TaskList} containing the tasks loaded from the file.
     * @throws JunoException If an error occurs while reading the file or if the file format is invalid.
     */
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
