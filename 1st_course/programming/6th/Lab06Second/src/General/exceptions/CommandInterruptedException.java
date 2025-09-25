package General.exceptions;

/**
 * An exception that represents an interruption in the execution of a command.
 */
public class CommandInterruptedException extends RuntimeException {

    /**
     * Constructs a new ServerMain.exceptions.CommandInterruptedException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public CommandInterruptedException(Exception cause) {
        super(cause);
    }

    public CommandInterruptedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
