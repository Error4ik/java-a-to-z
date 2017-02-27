package ru.job4j.controller;

import ru.job4j.exception.AlreadyOccupiedException;
import ru.job4j.exception.InvalidPointException;
import ru.job4j.model.Field;
import ru.job4j.model.Figure;
import ru.job4j.model.Point;

/**
 * It checks whether it is possible to put a figure in a cage, and if you can put it.
 *
 * @author Alexey Voronin.
 * @since 25.02.2017.
 */
public class MoveController {

    /**
     * Method set figure in the field.
     *
     * @param field  figure need to put on the field.
     * @param point  the cell where you want to put a figure.
     * @param figure figure.
     * @return true.
     * @throws InvalidPointException    if point invalid throw exception.
     * @throws AlreadyOccupiedException if cell is not free.
     */
    public boolean setFigure(final Field field, final Point point, final Figure figure)
            throws InvalidPointException, AlreadyOccupiedException {
        if (field.getFigure(point) != null) {
            throw new AlreadyOccupiedException("This cell is occupied");
        }
        field.setFigure(point, figure);
        return true;
    }
}
