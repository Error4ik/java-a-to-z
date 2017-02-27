package ru.job4j.model;

import ru.job4j.exception.InvalidPointException;

/**
 * The class of the playing field.
 *
 * @author Alexey Voronin.
 * @since 24.02.2017.
 */
public class Field {

    /**
     * Minimal value cell to field.
     */
    private static final int MIN_VALUE = 0;

    /**
     * Field containing game figure.
     */
    private final Figure[][] field;

    /**
     * Size field.
     */
    private final int fieldSize;

    /**
     * Constructor.
     *
     * @param size field size.
     */
    public Field(final int size) {
        this.fieldSize = size;
        this.field = new Figure[fieldSize][fieldSize];
    }

    /**
     * Set figure to the field.
     *
     * @param point  point to which install a figure.
     * @param figure figure.
     * @return true.
     * @throws InvalidPointException if point invalid throw exception.
     */
    public boolean setFigure(final Point point, final Figure figure) throws InvalidPointException {
        if (!checkPoint(point)) {
            throw new InvalidPointException("Invalid point!");
        }
        this.field[point.getX()][point.getY()] = figure;
        return true;
    }

    /**
     * @param point point to the which get a figure.
     * @return figure.
     * @throws InvalidPointException if point invalid throw exception.
     */
    public Figure getFigure(final Point point) throws InvalidPointException {
        if (!checkPoint(point)) {
            throw new InvalidPointException("Invalid point");
        }
        return this.field[point.getX()][point.getY()];
    }

    /**
     * Checked point to range the game field.
     *
     * @param point point.
     * @return true if point valid range.
     */
    private boolean checkPoint(final Point point) {
        return point.getX() >= MIN_VALUE
                && point.getY() >= MIN_VALUE
                && point.getX() < fieldSize
                && point.getY() < fieldSize;
    }

    /**
     * Get.
     *
     * @return fieldSize.
     */
    public int getFieldSize() {
        return this.fieldSize;
    }

    /**
     * Get.
     * @return field.
     */
    public Figure[][] getField() {
        return this.field;
    }
}
