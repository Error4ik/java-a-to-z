package ru.job4j;

import org.junit.Test;
import ru.job4j.model.Order;
import ru.job4j.view.ConsoleView;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Alexey Voronin.
 * @since 31.03.2017.
 */
public class ConsoleViewTest {

    /**
     * String separator.
     */
    private final String separator = System.getProperty("line.separator");

    /**
     * Method show.
     * Display the contents of the trees on the console.
     */
    @Test
    public void displayTheContentsOfTheTreesOnTheConsole() {
        final Map<Double, Order> buy = new TreeMap<>();
        final Map<Double, Order> sell = new TreeMap<>();
        final ConsoleView view = new ConsoleView();
        final String expectedValue = String.format(
                "BUY   PRICE  PRICE    SELL%s\t\t\t 120.0      70\n     70 130.0\n%s", separator, separator);

        buy.put(120d, new Order(null, null, 120d, 70, 0));
        sell.put(130d, new Order(null, null, 130d, 70, 0));

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        view.show(sell, buy);

        assertThat(out.toString(), is(expectedValue));
    }

}