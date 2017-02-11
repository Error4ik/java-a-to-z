package ru.job4j.exception;

/**
 * Exception divide by zero.
 */
public class DivideByZeroException extends ArithmeticException {

    /**
     * Constructor.
     *
     * @param message error message.
     */
    public DivideByZeroException(final String message) {
        super(message);
    }
}
