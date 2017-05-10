package ru.job4j;

/**
 * Figure.
 *
 * @author Alexey Voronin.
 * @since 09.05.2017.
 */
public abstract class Figure {

    /**
     * Field.
     */
    private final Field field;

    /**
     * Position figure.
     */
    private Point point;

    /**
     * Constructor.
     *
     * @param field field.
     */
    public Figure(final Field field) {
        this.field = field;
    }

    /**
     * Set.
     *
     * @param point new Point.
     */
    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * Get.
     *
     * @return point.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Get.
     *
     * @return field.
     */
    public Field getField() {
        return field;
    }

    /**
     * Motions of the figure by field.
     *
     * @param point point.
     */
    abstract void moveFigure(final Point point);
}
