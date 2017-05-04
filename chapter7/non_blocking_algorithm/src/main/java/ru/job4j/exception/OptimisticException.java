package ru.job4j.exception;

/**
 * RuntimeException.
 *
 * @author Alexey Voronin.
 * @since 04.05.2017.
 */
public class OptimisticException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param msg message.
     */
    public OptimisticException(final String msg) {
        super(msg);
    }
}
