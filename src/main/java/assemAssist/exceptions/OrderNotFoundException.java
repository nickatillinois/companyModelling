package assemAssist.exceptions;

/**
 * A class representing an illegal model exception.
 * Thrown when a CarOrder is requested but not found.
 *
 * @author SWOP team 10
 */
public class OrderNotFoundException extends Exception {

    /**
     * Creates a new OrderNotFoundException given a message.
     *
     * @param message Specifies why the exception was called.
     * @throws IllegalArgumentException if string is null or the empty string
     */
    public OrderNotFoundException(String message) {
        super(message);
        if (message == null || message.length() == 0) throw new IllegalArgumentException("Specify " +
                "why the exception needs to be called");
    }
}