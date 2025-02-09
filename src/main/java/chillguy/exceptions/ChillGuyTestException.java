package chillguy.exceptions;

/**
 * Represents a custom exception class for ChillGuy tests that extends {@link Exception}.
 * <p>
 * This exception class is used specifically for testing scenarios in the ChillGuy chatbot and inherits from the
 * {@link Exception} class to provide error messages and handling for such cases.
 */
public class ChillGuyTestException extends Exception {
    /**
     * Default constructor for {@code ChillGuyTestException}.
     * <p>
     * Calls the constructor of the parent {@link ChillGuyException} class.
     */
    public ChillGuyTestException(String line) {
        super(line);
    }
}
