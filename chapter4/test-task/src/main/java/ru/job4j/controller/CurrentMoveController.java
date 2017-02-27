package ru.job4j.controller;

import ru.job4j.exception.InvalidPointException;
import ru.job4j.model.Field;
import ru.job4j.model.Figure;
import ru.job4j.model.Point;

/**
 * Determines who goes at the moment.
 *
 * @author Alexey Voronin.
 * @since 25.02.2017.
 */
public class CurrentMoveController {

    /**
     * Method checks whose turn it is to walk.
     *
     * @param field field to check.
     * @return figure whose turn or null if there are no empty cells.
     * @throws InvalidPointException if point invalid throw exception.
     */
    public Figure currentMove(final Field field) throws InvalidPointException {
        int count = 0;
        for (int i = 0; i < field.getFieldSize(); i++) {
            for (int j = 0; j < field.getFieldSize(); j++) {
                if (field.getFigure(new Point(i, j)) != null) {
                    count++;
                }
            }
        }
        if (count == field.getFieldSize() * field.getFieldSize()) {
            return null;
        }
        return count % 2 == 0 ? Figure.X : Figure.O;
    }
}
