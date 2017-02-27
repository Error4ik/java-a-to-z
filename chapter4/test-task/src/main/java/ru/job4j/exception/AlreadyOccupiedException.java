package ru.job4j.exception;

/**
 * Thrown when cell field is not empty.
 *
 * @author Alexey Voronin.
 * @since 25.02.2017.
 */
public class AlreadyOccupiedException extends Exception {

    /**
     * Constructor.
     *
     * @param message error message.
     */
    public AlreadyOccupiedException(final String message) {
        super(message);
    }
}
