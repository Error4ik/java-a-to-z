package ru.job4j.update_tracker.input;

/**
 * User input.
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
