package ru.job4j.triangle;

import ru.job4j.point.Point;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * Тест вычисления площади треугольника.
 * @author Alexey.
 */
public class TriangleTest {

    /**
     * Тест площади треугольнка с координатами больше 0.
     */
    @Test
    public void thenAreaTriangleThatValidPoint() {
	final Point p1 = new Point(3, 5);
        final Point p2 = new Point(4, 8);
        final Point p3 = new Point(6, 3);
        Triangle triangle = new Triangle(p1, p2, p3);
        final double area = 5.5;
        final double error = 0.03;
        assertThat(area, closeTo(triangle.area(), error));
    }

    /**
     * Тест площади треугольнка с координатами равными 0.
     */
    @Test
    public void thenAreaTriangleThatZeroPoint() {
        Triangle triangle = new Triangle(new Point(1, 1), new Point(1, 1), new Point(1, 1));
        final double area = 0d;
        final double error = 0.03;
        assertThat(area, closeTo(triangle.area(), error));
    }
}