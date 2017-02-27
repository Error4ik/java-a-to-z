package ru.job4j.exception;

/**
 * Thrown when an point out of range.
 *
 * @author Alexey Voronin.
 * @since 25.02.2017.
 */
public class InvalidPointException extends Exception {

    /**
     * Constructor.
     *
     * @param message error message.
     */
    public InvalidPointException(final String message) {
        super(message);
    }
}
