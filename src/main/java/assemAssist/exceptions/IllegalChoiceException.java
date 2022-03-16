package assemAssist.exceptions;

/**
 * A class representing an illegal choice exception.
 * Thrown when a choice is chosen that is not offered for a component of a car.
 *
 * @author SWOP team 10
 */
public class IllegalChoiceException extends Exception {

    /**
     * Creates a new illegal choice exception given a message.
     *
     * @param message Specifies why the exception was called.
     * @throws IllegalArgumentException if string is null or the empty string
     */
    public IllegalChoiceException(String message) {
        super(message);
        if(message == null || message.length() == 0) throw new IllegalArgumentException("Specify " +
                "why the exception needs to be called");
    }

}
