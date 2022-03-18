package assemAssist.exceptions;

public class IllegalCompletionDateException extends Exception{
    /**
     * Creates a new illegal completion date exception given a message.
     *
     * @param message Specifies why the exception was called.
     * @throws IllegalArgumentException if string is null or the empty string
     */
    public IllegalCompletionDateException(String message) {
        super(message);
        if(message == null || message.length() == 0) throw new IllegalArgumentException("Specify " +
                "why the exception needs to be called");
    }

}