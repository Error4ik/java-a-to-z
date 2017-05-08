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

    public AFigure(final Field field, final Point point, final int move) {
        this.field = field;
        this.point = point;
        this.move = move;
        this.random = new Random();
    }

    abstract public Point getMovePoint(final int value);

    @Override
    public String toString() {
        return "[" + this.point.getX() + ":" + this.point.getY() + "]";
    }

    public Point getPoint() {
        return point;
    }

    public Field getField() {
        return field;
    }

    public Random getRandom() {
        return random;
    }

    public int getMove() {
        return move;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
