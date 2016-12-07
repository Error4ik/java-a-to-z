package ru.job4j.calculate_test;

import ru.job4j.calculate.Calculate;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Alexey Voronin
 * @version 1
 */

public class CalculateTest {
    /**
     * Test add.
     */
    @Test
    public void testShowText() {
        Calculate calc = new Calculate();
        String actualText = calc.showText("World");
        String expected = "World World World";
        assertThat(expected, is(actualText));
    }
}