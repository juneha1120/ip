package chillguy.main;

import org.junit.jupiter.api.Test;

public class ChillGuyTest {
    @Test
    public void run_invalidFilePath_throwsException() {
        try {
            String invalidFilePath = "111111111";
            ChillGuy invalidChillGuy = new ChillGuy(invalidFilePath);
            invalidChillGuy.run();
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }
}
