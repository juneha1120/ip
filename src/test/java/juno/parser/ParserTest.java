package juno.parser;

import juno.commands.TryAgainCommand;
import juno.enums.TaskType;
import juno.exceptions.JunoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    @Test
    public void parse_nullString_throwsException() {
        try {
            String nullString = null;
            new Parser().parse(nullString);
        } catch (JunoException | NullPointerException ignored) {
        }
    }

    @Test
    public void parse_nonExistingStringCommand_returnsTryAgainCommand() {
        String nonExistingStringCommand = "nonExistingStringCommand";
        try {
            assertInstanceOf(TryAgainCommand.class, new Parser().parse(nonExistingStringCommand));
        } catch (JunoException ignored) {
        }
    }

    @Test
    public void isTimeArgument_nullString_throwsException() {
        try {
            String nullString = null;
            new Parser().isTimeArgument(nullString);
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    public void prepareAddCommand_nullTaskType_throwsException() {
        try {
            String exampleString = "Task 1";
            new Parser().prepareAddCommand(null, exampleString);
        } catch (JunoException | NullPointerException ignored) {
        }
    }

    @Test
    public void prepareAddCommand_emptyString_throwsException() {
        try {
            new Parser().prepareAddCommand(TaskType.TODO, "");
        } catch (JunoException ignored) {
        }
    }

    @Test
    public void prepareShowWithDateCommand_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            new Parser().prepareShowWithDateCommand(invalidString);
        } catch (JunoException ignored) {
        }
    }

    @Test
    public void prepareMarkCommand_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            new Parser().prepareMarkCommand(invalidString);
        } catch (JunoException ignored) {
        }
    }

    @Test
    public void prepareUnmarkCommand_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            new Parser().prepareUnmarkCommand(invalidString);
        } catch (JunoException ignored) {
        }
    }

    @Test
    public void prepareDeleteCommand_invalidString_throwsException() {
        try {
            String invalidString = "invalid";
            new Parser().prepareDeleteCommand(invalidString);
        } catch (JunoException ignored) {
        }
    }
}
