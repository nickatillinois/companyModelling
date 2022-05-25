package assemAssist.exceptions;

/**
 * A class representing an illegal constraint exception.
 * Thrown when a constraint is not in the correct format.
 *
 * @author SWOP team 10
 */
public class IllegalConstraintException extends Exception {

    /**
     * Creates a new illegal choice exception given a message.
     *
     * @param message Specifies why the exception was called.
     * @throws IllegalArgumentException if string is null or the empty string
     */
    public IllegalConstraintException(String message) {
        super(message);
        if(message == null || message.length() == 0) throw new IllegalArgumentException("Specify " +
                "why the exception needs to be called");
    }

}