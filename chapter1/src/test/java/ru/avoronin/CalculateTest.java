package ru.avoronin;

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
        assertThat(actualText, is("World World World"));
    }
}