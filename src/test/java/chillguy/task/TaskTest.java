package chillguy.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void task_nullTaskName_throwsException() {
        assertThrows(AssertionError.class, () -> new Task(null));
    }
}
