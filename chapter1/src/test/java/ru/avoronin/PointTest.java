package ru.avoronin;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Тест.
 */
public class PointTest {

    /**
     * Тест метода distanceTo.
     */
    @Test
    public void testDistanceTo() {
        final double coordinate1 = 3;
        final double coordinate2 = 4;
        final double result = 1.41;
        final double error = 0.03;
        Point point1 = new Point(coordinate1, coordinate1);
        Point point2 = new Point(coordinate2, coordinate2);
        assertThat(point1.distanceTo(point2), closeTo(result, error));
    }
}