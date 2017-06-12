package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;

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
     * @param tracker   menu_tracker.
     * @param inputData user input.
     */
    void execute(final ITracker tracker, final Input inputData);
}
