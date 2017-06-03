package ru.job4j.update_tracker.action;

import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.tracker.Tracker;

/**
 * Interface for actions.
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