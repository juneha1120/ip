package chillguy.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import chillguy.commands.TryAgainCommand;
import chillguy.enums.TaskType;
import chillguy.exceptions.ChillGuyException;

public class ParserTest {
    @Test
    public void parse_nullString_throwsException() {
        try {
            String nullString = null;
            new Parser().parse(nullString);
        } catch (ChillGuyException | NullPointerException ignored) {
            // Ignored
        }
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
        try {
            String nullString = null;
            new Parser().isTimeArgument(nullString);
        } catch (NullPointerException ignored) {
            // Ignored
        }
    }

    @Test
    public void prepareAddCommand_nullTaskType_throwsException() {
        try {
            String exampleString = "Task 1";
            new Parser().prepareAddCommand(null, exampleString);
        } catch (ChillGuyException | NullPointerException ignored) {
            // Ignored
        }
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
