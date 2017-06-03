package ru.job4j.input;

/**
 * User input.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public interface Input {

    /**
     * Returns user input.
     *
     * @param msg message to user.
     * @return user input.
     * @throws NumberFormatException If, when selecting a menu item, not a number, but a string.
     */
    String getInput(String msg);
}
