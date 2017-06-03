package ru.job4j.update_tracker.exception;

/**
 * Throws when you go beyond the menu items.
 */
public class MenuOutException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param msg message.
     */
    public MenuOutException(final String msg) {
        System.out.print(msg);
    }
}
