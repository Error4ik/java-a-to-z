package ru.job4j.controller;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.exception.AlreadyOccupiedException;
import ru.job4j.model.Field;
import ru.job4j.model.Figure;
import ru.job4j.model.Point;

import static org.junit.Assert.assertTrue;

/**
 * MoveController class.
 *
 * @author Alexey Voronin.
 * @since 27.02.2017.
 */
public class MoveControllerTest {

    /**
     * Field to game.
     */
    private Field field;

    /**
     * Init field.
     */
    @Before
    public void init() {
        field = new Field(3);
    }

    /**
     * Method setFigure. Return true.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenMoveIsValidThenReturnTrue() throws Exception {
        final MoveController controller = new MoveController();
        final Point inputPoint = new Point(0, 0);
        final Figure inputFigure = Figure.O;

        assertTrue(controller.setFigure(field, inputPoint, inputFigure));
    }

    /**
     * Method setFigure. Throw AlreadyOccupiedException.
     *
     * @throws Exception exception.
     */
    @Test(expected = AlreadyOccupiedException.class)
    public void whenPointOccupiedFigureThenThrowAlreadyOccupiedException() throws Exception {
        final MoveController controller = new MoveController();
        final Point inputPoint = new Point(0, 0);
        final Figure inputFigure = Figure.O;

        field.setFigure(inputPoint, inputFigure);

        controller.setFigure(field, inputPoint, Figure.X);
    }
}