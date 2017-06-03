package ru.job4j.view;

/**
 * View.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public interface IView {

    /**
     * Print a line.
     *
     * @param string line.
     */
    void execute(final String string);
}
