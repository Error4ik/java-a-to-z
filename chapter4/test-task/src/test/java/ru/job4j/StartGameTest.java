package ru.job4j;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.exception.InvalidPointException;
import ru.job4j.model.Field;
import ru.job4j.model.Figure;
import ru.job4j.model.Point;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * StartGame class.
 *
 * @author Alexey Voronin.
 * @since 27.02.2017.
 */
public class StartGameTest {

    /**
     * Separator.
     */
    private final String sep = System.getProperty("line.separator");

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
     * Method getName. Return valid name.
     */
    @Test
    public void whenGetNameThenReturnValidName() {
        final StartGame startGame = new StartGame("XO", field);
        final String expectedValue = "XO";

        assertThat(startGame.getName(), is(expectedValue));
    }

    /**
     * Method getField. Return valid field.
     */
    @Test
    public void whenGetFieldThenReturnValidField() {
        final StartGame startGame = new StartGame("XO", field);
        final Field expectedField = field;

        assertThat(startGame.getField(), is(expectedField));
    }

    /**
     * Method move. Show congratulation winner X by console.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenWinnerXThenShowCongratulationWinnerByConsole() throws Exception {
        final String expectedValue = String.format("%sCongratulation winner: %s", sep, Figure.X);
        field.setFigure(new Point(0, 0), Figure.X);
        field.setFigure(new Point(0, 1), Figure.X);
        field.setFigure(new Point(0, 2), Figure.X);

        final StartGame startGame = new StartGame("XO", field);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        startGame.move("Z");

        assertThat(out.toString(), is(expectedValue));
    }

    /**
     * Method move. If field not empty point, show by console draw.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenTheFieldIsNotEmptyCellsThenShowByConsoleDraw() throws Exception {
        final String expectedValue = String.format("Draw!!!%s", sep);
        field.setFigure(new Point(0, 0), Figure.X);
        field.setFigure(new Point(0, 2), Figure.X);
        field.setFigure(new Point(1, 1), Figure.X);
        field.setFigure(new Point(2, 1), Figure.X);
        field.setFigure(new Point(0, 1), Figure.O);
        field.setFigure(new Point(1, 0), Figure.O);
        field.setFigure(new Point(1, 2), Figure.O);
        field.setFigure(new Point(2, 0), Figure.O);
        field.setFigure(new Point(2, 2), Figure.O);
        final StartGame startGame = new StartGame("", field);

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        startGame.move("");

        assertThat(out.toString(), is(expectedValue));
    }

    /**
     * Method move. First move to player.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenPlayerChoiceFigureXThenFirstMove() throws Exception {
        final Figure expectedFigure = Figure.X;
        final String inputValue = "0 0";
        final StartGame startGame = new StartGame("", field);
        final ByteArrayInputStream input = new ByteArrayInputStream(inputValue.getBytes());
        System.setIn(input);
        startGame.move("X");

        assertThat(field.getFigure(new Point(0, 0)), is(expectedFigure));
    }

    /**
     * Method move. Computer move.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenIfPlayerChoiceFigureOThenComputerFirstMove() throws Exception {
        final Figure expectedFigure = Figure.X;
        final StartGame startGame = new StartGame("", field);
        startGame.move("O");

        assertThat(this.findFigureToField(), is(expectedFigure));
    }

    /**
     * Search figure to field.
     *
     * @return searching figure.
     * @throws InvalidPointException exception.
     */
    private Figure findFigureToField() throws InvalidPointException {
        Figure findFigure = null;
        for (int i = 0; i < field.getFieldSize(); i++) {
            for (int j = 0; j < field.getFieldSize(); j++) {
                if (this.field.getField()[i][j] != null) {
                    findFigure = this.field.getFigure(new Point(i, j));
                }
            }
        }
        return findFigure;
    }
}