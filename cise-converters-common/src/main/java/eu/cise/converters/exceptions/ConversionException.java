package eu.cise.converters.exceptions;

/**
 * Class represents a custom exception type for handling errors during conversion processes.
 */
public class ConversionException extends RuntimeException {

    /**
     * Constructs a new conversion exception with the specified detail message.
     *
     * @param message the detail message
     */
    public ConversionException(String message) {
        super(message);
    }

    /**
     * Constructs a new conversion exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param exception the cause of the exception
     */
    public ConversionException(String message, Throwable exception) {
        super(message, exception);
    }

    /**
     * Constructs a new conversion exception with the specified cause.
     *
     * @param exception the cause of the exception
     */
    public ConversionException(Throwable exception) {
        super(exception);
    }
}
