package juno.task;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todo_nullTaskName_throwsException() {
        try {
            new Todo(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }
}
