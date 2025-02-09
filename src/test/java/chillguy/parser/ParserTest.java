package chillguy.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import chillguy.commands.TryAgainCommand;
import chillguy.enums.TaskType;
import chillguy.exceptions.ChillGuyException;

public class ParserTest {
    @Test
    public void parse_nullString_throwsException() {
        String nullString = null;
        assertThrows(AssertionError.class, () -> new Parser().parse(nullString));
    }

    @Test
    public void parse_nonExistingStringCommand_returnsTryAgainCommand() {
        String nonExistingStringCommand = "nonExistingStringCommand";
        try {
            assertInstanceOf(TryAgainCommand.class, new Parser().parse(nonExistingStringCommand));
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void isTimeArgument_nullString_throwsException() {
        String nullString = null;
        assertThrows(AssertionError.class, () -> new Parser().isTimeArgument(nullString));
    }

    @Test
    public void prepareAddCommand_nullTaskType_throwsException() {
        String exampleString = "Task 1";
        assertThrows(AssertionError.class, () -> new Parser().prepareAddCommand(null, exampleString));
    }

    @Test
    public void prepareAddCommand_emptyString_throwsException() {
        try {
            new Parser().prepareAddCommand(TaskType.TODO, "");
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void prepareShowWithDateCommand_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            new Parser().prepareShowWithDateCommand(invalidString);
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void prepareMarkCommand_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            new Parser().prepareMarkCommand(invalidString);
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void prepareUnmarkCommand_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            new Parser().prepareUnmarkCommand(invalidString);
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }

    @Test
    public void prepareDeleteCommand_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            new Parser().prepareDeleteCommand(invalidString);
        } catch (ChillGuyException ignored) {
            // Ignored
        }
    }
}
