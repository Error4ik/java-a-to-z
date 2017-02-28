package ru.job4j.exception;

/**
 * Not found key exception.
 *
 * @author Alexey Voronin.
 * @since 28.02.2017.
 */
public class NotFoundKeyException extends Exception{

    /**
     * Constructor.
     * @param message message.
     */
    public NotFoundKeyException(final String message) {
        super(message);
    }
}
