package ru.job4j;

/**
 * Bomber.
 *
 * @author Alexey Voronin.
 * @since 09.05.2017.
 */
public class Bomber extends Figure {

    /**
     * Constructor.
     *
     * @param field field.
     */
    public Bomber(final Field field) {
        super(field);
    }

    @Override
    void moveFigure(final Point point) {
        Cell cell = this.getField().getCell(point);
        if (cell != null && cell.getLock().tryLock()) {
            Cell oldCell = this.getField().getCell(this.getPoint());
            try {
                this.setPoint(point);
            } finally {
                oldCell.getLock().unlock();
            }
        }
    }
}
