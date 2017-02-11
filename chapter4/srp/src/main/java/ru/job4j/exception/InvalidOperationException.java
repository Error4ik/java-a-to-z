package ru.job4j.exception;

/**
 * Invalid type operation.
 */
public class InvalidOperationException extends Exception {

    /**
     * Constructor.
     *
     * @param message error message.
     */
    public InvalidOperationException(final String message) {
        super(message);
    }
}
