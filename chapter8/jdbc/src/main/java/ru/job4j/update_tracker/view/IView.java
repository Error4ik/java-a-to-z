package ru.job4j.update_tracker.view;

/**
 * View.
 *
 * @author Alexey Voronin.
 * @since 02.06.2017.
 */
public interface IView {

    /**
     * Print a line.
     *
     * @param string line.
     */
    void execute(final String string);
}
