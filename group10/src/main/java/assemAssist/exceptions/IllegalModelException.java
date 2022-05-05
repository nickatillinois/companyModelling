package assemAssist.exceptions;

/**
 * A class representing an illegal model exception.
 * Thrown when a model is chosen that is not offered.
 *
 * @author SWOP team 10
 */
public class IllegalModelException extends Exception {

        /**
         * Creates a new illegal model exception given a message.
         *
         * @param message Specifies why the exception was called.
         * @throws IllegalArgumentException if string is null or the empty string
         */
        public IllegalModelException(String message) {
            super(message);
            if (message == null || message.length() == 0) throw new IllegalArgumentException("Specify " +
                    "why the exception needs to be called");
        }
}