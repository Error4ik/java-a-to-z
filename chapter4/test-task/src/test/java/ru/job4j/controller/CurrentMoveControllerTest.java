package ru.job4j.controller;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Field;
import ru.job4j.model.Figure;
import ru.job4j.model.Point;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * CurrentMoveController class.
 *
 * @author Alexey Voronin.
 * @since 27.02.2017.
 */
public class CurrentMoveControllerTest {

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
     * Method currentMove. Return X.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenTheFigureGoesXThenReturnsX() throws Exception {
        final Figure figureX = Figure.X;
        final Figure figureO = Figure.O;
        final Figure expectedFigure = Figure.X;
        final CurrentMoveController controller = new CurrentMoveController();

        field.setFigure(new Point(0, 0), figureX);
        field.setFigure(new Point(1, 1), figureO);

        assertThat(controller.currentMove(field), is(expectedFigure));
    }

    /**
     * Method currentMove. Return O.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenTheFigureGoesOThenReturnsO() throws Exception {
        final Figure figureX = Figure.X;
        final Figure expectedFigure = Figure.O;
        final CurrentMoveController controller = new CurrentMoveController();

        field.setFigure(new Point(0, 0), figureX);

        assertThat(controller.currentMove(field), is(expectedFigure));
    }

    /**
     * Method currentMove. If the field is filled, return null.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenTheFieldIsFullThenReturnNull() throws Exception {
        final CurrentMoveController controller = new CurrentMoveController();

        field.setFigure(new Point(0, 0), Figure.X);
        field.setFigure(new Point(0, 1), Figure.O);
        field.setFigure(new Point(0, 2), Figure.O);
        field.setFigure(new Point(1, 0), Figure.X);
        field.setFigure(new Point(1, 1), Figure.X);
        field.setFigure(new Point(1, 2), Figure.O);
        field.setFigure(new Point(2, 0), Figure.X);
        field.setFigure(new Point(2, 1), Figure.O);
        field.setFigure(new Point(2, 2), Figure.X);

        assertNull(controller.currentMove(field));
    }
}