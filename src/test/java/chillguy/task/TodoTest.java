package chillguy.task;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todo_nullTaskName_throwsException() {
        assertThrows(AssertionError.class, () -> new Todo(null));
    }
}
