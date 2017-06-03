package ru.job4j.action;

import ru.job4j.input.Input;
import ru.job4j.tracker.Tracker;

/**
 * Interface for actions.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public interface Action {

    /**
     * Returns the number under which this action is in the menu.
     *
     * @return number.
     */
    String getId();

    /**
     * Displaying information about the action.
     *
     * @return string with the number and name of the action.
     */
    String showItem();

    /**
     * Performs all the main work of the menu item.
     *
     * @param tracker   tracker.
     * @param inputData user input.
     */
    void execute(final Tracker tracker, final Input inputData);
}
