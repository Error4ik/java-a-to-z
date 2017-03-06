package ru.job4j;

import java.util.Objects;

/**
 * Class Point.
 *
 * @author Alexey Voronin.
 * @since 03.03.2017.
 */
public class Point {

    /**
     * Coordinate x.
     */
    private final int x;

    /**
     * Coordinate y.
     */
    private final int y;

    /**
     * Constructor init coordinate.
     *
     * @param x coordinate x.
     * @param y coordinate y.
     */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get.
     *
     * @return coordinate x.
     */
    public int getX() {
        return x;
    }

    /**
     * Get.
     *
     * @return coordinate y.
     */
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("Point {x = %d, y = %d}", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        Point point = (Point) o;
        return getX() == point.getX() && getY() == point.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
