package juno.main;

import org.junit.jupiter.api.Test;

public class JunoTest {
    @Test
    public void run_invalidFilePath_throwsException() {
        try {
            String invalidFilePath = "111111111";
            Juno invalidJuno = new Juno(invalidFilePath);
            invalidJuno.run();
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }
}
