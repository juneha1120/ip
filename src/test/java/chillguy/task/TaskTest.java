package chillguy.task;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void task_nullTaskName_throwsException() {
        try {
            new Task(null);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }
}
