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
 * WinnerController class.
 *
 * @author Alexey Voronin.
 * @since 27.02.2017.
 */
public class WinnerControllerTest {

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
     * Method getWinner. Return horisontally winner X.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenHorisontallyWinnerFigureXThenReturnWinnerFigureX() throws Exception {
        final Figure expectedWinner = Figure.X;
        final WinnerController controller = new WinnerController();

        field.setFigure(new Point(0, 0), Figure.X);
        field.setFigure(new Point(0, 1), Figure.X);
        field.setFigure(new Point(0, 2), Figure.X);

        assertThat(controller.getWinner(field), is(expectedWinner));
    }

    /**
     * Method getWinner. Return vertical winner O.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenVerticalWinnerFigureOThenReturnWinnerFigureO() throws Exception {
        final Figure expectedFigure = Figure.O;
        final WinnerController controller = new WinnerController();

        field.setFigure(new Point(0, 0), Figure.O);
        field.setFigure(new Point(1, 0), Figure.O);
        field.setFigure(new Point(2, 0), Figure.O);

        assertThat(controller.getWinner(field), is(expectedFigure));
    }

    /**
     * Method getWinner. Return diagonal winner from top left to bottom right, winner X.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenDiagonalWinnerFromTopLeftToBottomRightThenReturnWinnerX() throws Exception {
        final Figure expectedFigure = Figure.X;
        final WinnerController controller = new WinnerController();

        field.setFigure(new Point(0, 0), Figure.X);
        field.setFigure(new Point(1, 1), Figure.X);
        field.setFigure(new Point(2, 2), Figure.X);

        assertThat(controller.getWinner(field), is(expectedFigure));
    }

    /**
     * Method getWinner. Return diagonal winner from top right to bottom left, winner O.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenDiagonalWinnerFromTopRightToBottomLeftThenReturnWinnerO() throws Exception {
        final Figure expectedFigure = Figure.O;
        final WinnerController controller = new WinnerController();

        field.setFigure(new Point(0, 2), Figure.O);
        field.setFigure(new Point(1, 1), Figure.O);
        field.setFigure(new Point(2, 0), Figure.O);

        assertThat(controller.getWinner(field), is(expectedFigure));
    }

    /**
     * Method getWinner. If winner not found return null.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenWinnerNotFoundThenReturnNull() throws Exception {
        final WinnerController controller = new WinnerController();

        field.setFigure(new Point(0, 0), Figure.O);
        field.setFigure(new Point(1, 1), Figure.X);
        field.setFigure(new Point(2, 0), Figure.O);

        assertNull(controller.getWinner(field));
    }
}