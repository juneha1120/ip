package juno.exceptions;

/**
 * Represents a custom exception class for Juno tests that extends {@link JunoException}.
 * <p>
 * This exception class is used specifically for testing scenarios in the Juno chatbot and inherits from the
 * {@link JunoException} class to provide error messages and handling for such cases.
 */
public class JunoTestException extends JunoException {
    /**
     * Default constructor for {@code JunoTestException}.
     * <p>
     * Calls the constructor of the parent {@link JunoException} class.
     */
    public JunoTestException() {
        super();
    }
}
