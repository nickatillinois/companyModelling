package assemAssist.exceptions;

/**
 * A class representing an exception that is thrown when an option is selected, but not the component implied by option A.
 *
 * @author SWOP team 10
 */
public class OptionThenComponentException extends Exception {

    /**
     * Creates a new OptionThenComponentException given a message.
     *
     * @param message Specifies why the exception was called.
     * @throws IllegalArgumentException if string is null or the empty string
     */
    public OptionThenComponentException(String message) {
        super(message);
        if (message == null || message.length() == 0) throw new IllegalArgumentException("Specify " +
                "why the exception needs to be called");
    }
}