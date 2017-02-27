package ru.job4j.model;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.exception.InvalidPointException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * Test class Field.
 *
 * @author Alexey Voronin.
 * @since 27.02.2017.
 */
public class FieldTest {

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
     * Method getFieldSize.
     */
    @Test
    public void whenGetFieldSizeThenReturnValidValue() {
        final int expectedValue = 3;

        assertThat(field.getFieldSize(), is(expectedValue));
    }

    /**
     * Method setFigure, return true.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenSetFigureValidPointThenFigureAddField() throws Exception {
        final Figure inputFigure = Figure.X;
        final Point inputPoint = new Point(0, 0);

        assertTrue(field.setFigure(inputPoint, inputFigure));
    }

    /**
     * Method setFigure, throw exception.
     *
     * @throws Exception exception.
     */
    @Test(expected = InvalidPointException.class)
    public void whenPointIsNotValidThenThrowException() throws Exception {
        final Figure inputFigure = Figure.X;
        final Point inputPoint = new Point(3, 3);

        field.setFigure(inputPoint, inputFigure);
    }

    /**
     * Method getFigure, return valid figure.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenGetFigureThenReturnValidFigure() throws Exception {
        final Figure inputFigure = Figure.O;
        final Point inputPoint = new Point(0, 0);
        final Figure expectedFigure = Figure.O;

        field.setFigure(inputPoint, inputFigure);

        assertThat(field.getFigure(inputPoint), is(expectedFigure));
    }

    /**
     * Method getFigure, throw exception.
     *
     * @throws Exception exception.
     */
    @Test(expected = InvalidPointException.class)
    public void whenGetFigureInvalidPointThenThrowException() throws Exception {
        final Point inputPoint = new Point(4, 5);

        field.getFigure(inputPoint);
    }

    /**
     * Method getField.
     */
    @Test
    public void whenGetFieldThenReturnValidField() {
        final int arraySize = 3;
        final Figure[][] figures = new Figure[arraySize][arraySize];
        assertThat(field.getField(), is(figures));
    }
}