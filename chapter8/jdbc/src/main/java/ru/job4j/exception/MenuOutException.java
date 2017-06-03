package ru.job4j.exception;

/**
 * Throws when you go beyond the menu items.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
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
