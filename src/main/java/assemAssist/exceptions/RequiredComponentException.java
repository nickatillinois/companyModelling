package assemAssist.exceptions;

/**
 * A class representing an exception thrown because an essential component is missing.
 *
 * @author SWOP team 10
 */
public class RequiredComponentException extends Exception {

    /**
     * Creates a new illegal model exception given a message.
     *
     * @param message Specifies why the exception was called.
     * @throws IllegalArgumentException if string is null or the empty string
     */
    public RequiredComponentException(String message) {
        super(message);
        if (message == null || message.length() == 0) throw new IllegalArgumentException("Specify " +
                "why the exception needs to be called");
    }
}