package assemAssist.exceptions;

/**
 * A class representing an exception that is thrown when an option A is selected, but not the options implied by option A.
 *
 * @author SWOP team 10
 */
public class OptionAThenOptionBException extends Exception {

    /**
     * Creates a new OptionAThenOptionBException given a message.
     *
     * @param message Specifies why the exception was called.
     * @throws IllegalArgumentException if string is null or the empty string
     */
    public OptionAThenOptionBException(String message) {
        super(message);
        if (message == null || message.length() == 0) throw new IllegalArgumentException("Specify " +
                "why the exception needs to be called");
    }
}