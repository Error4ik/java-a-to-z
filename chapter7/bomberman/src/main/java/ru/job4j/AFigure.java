package ru.job4j;

import java.util.Random;

/**
 * Abstract figure.
 *
 * @author Alexey Voronin.
 * @since 08.05.2017.
 */
public abstract class AFigure implements Runnable {

    /**
     * Point.
     */
    private Point point;

    /**
     * Field.
     */
    private final Field field;

    /**
     * Random.
     */
    private final Random random;

    /**
     * Variants of the move.
     */
    private final int move;

    /**
     * Constructor.
     * @param field field.
     * @param point point.
     * @param move variant of the move.
     */
    public AFigure(final Field field, final Point point, final int move) {
        this.field = field;
        this.point = point;
        this.move = move;
        this.random = new Random();
    }

    /**
     * Move figure.
     *
     * @param value variant of the move.
     * @return point.
     */
    abstract Point getMovePoint(final int value);

    @Override
    public String toString() {
        return "[" + this.point.getX() + ":" + this.point.getY() + "]";
    }

    /**
     * Get.
     *
     * @return point.
     */
    public Point getPoint() {
        return this.point;
    }

    /**
     * Get.
     *
     * @return field.
     */
    public Field getField() {
        return this.field;
    }

    /**
     * Get.
     *
     * @return random.
     */
    public Random getRandom() {
        return this.random;
    }

    /**
     * Get.
     *
     * @return move.
     */
    public int getMove() {
        return this.move;
    }

    /**
     * Set.
     *
     * @param point new point.
     */
    public void setPoint(Point point) {
        this.point = point;
    }
}
