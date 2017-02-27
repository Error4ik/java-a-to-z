package ru.job4j.view;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.model.Field;
import ru.job4j.model.Figure;
import ru.job4j.model.Point;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * ConsoleView class.
 *
 * @author Alexey Voronin.
 * @since 27.02.2017.
 */
public class ConsoleViewTest {

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
     * Method showMessage.
     */
    @Test
    public void whenShowMessageTakeStringThenOutputStringByConsole() {
        final IView view = new ConsoleView();
        final String expectedValue = String.format("Test message!%s", sep);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        view.showMessage("Test message!");
        assertThat(out.toString(), is(expectedValue));
    }

    /**
     * Method showField.  Show empty field.
     */
    @Test
    public void whenFieldIsEmptyThenShowByConsoleEmptyField() {
        final IView view = new ConsoleView();
        final StringBuilder sb = new StringBuilder();
        sb.append("   ").append("|").append("   ").append("|").append("   ")
                .append(sep)
                .append("~~~~~~~~~~~~").append(sep)
                .append("   ").append("|").append("   ").append("|").append("   ")
                .append(sep)
                .append("~~~~~~~~~~~~").append(sep)
                .append("   ").append("|").append("   ").append("|").append("   ")
                .append(sep);
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        view.showField(field);

        assertThat(out.toString(), is(sb.toString()));
    }

    /**
     * Method showField.  Show empty field.
     *
     * @throws Exception exception.
     */
    @Test
    public void whenFieldIsNotEmptyThenShowByConsoleNotEmptyField() throws Exception {
        final IView view = new ConsoleView();
        field.setFigure(new Point(0, 0), Figure.X);
        field.setFigure(new Point(0, 1), Figure.O);
        field.setFigure(new Point(0, 2), Figure.X);
        field.setFigure(new Point(2, 0), Figure.O);
        field.setFigure(new Point(2, 2), Figure.X);

        final StringBuilder sb = new StringBuilder();
        sb.append(" X ").append("|").append(" O ").append("|").append(" X ").append(sep)
                .append("~~~~~~~~~~~~").append(sep)
                .append("   ").append("|").append("   ").append("|").append("   ").append(sep)
                .append("~~~~~~~~~~~~").append(sep)
                .append(" O ").append("|").append("   ").append("|").append(" X ").append(sep);


        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        view.showField(field);

        assertThat(out.toString(), is(sb.toString()));
    }
}