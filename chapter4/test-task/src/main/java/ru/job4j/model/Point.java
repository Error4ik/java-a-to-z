package ru.job4j.model;

/**
 * Class point.
 *
 * @author Alexey Voronin.
 * @since 24.02.2017.
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
}
