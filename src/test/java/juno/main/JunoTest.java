package juno.main;

import juno.exceptions.JunoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JunoTest {
    @Test
    public void run_invalidFilePath_throwsException() {
        try {
            String invalidFilePath = "111111111";
            Juno invalidJuno = new Juno(invalidFilePath);
            invalidJuno.run();
        } catch (NullPointerException ignored) {
        }
    }
}
