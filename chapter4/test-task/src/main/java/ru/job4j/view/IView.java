package ru.job4j.view;

import ru.job4j.model.Field;

/**
 * Interface view to game.
 *
 * @author Alexey Voronin.
 * @since 25.02.2017.
 */
public interface IView {

    /**
     * Print field to console.
     *
     * @param field field.
     */
    void showField(final Field field);

    /**
     * Show any message.
     * @param message message.
     */
    void showMessage(final String message);
}
