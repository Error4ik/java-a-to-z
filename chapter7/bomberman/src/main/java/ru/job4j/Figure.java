package ru.job4j;

import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Any figure.
 *
 * @author Alexey Voronin.
 * @since 07.05.2017.
 */
public class Figure implements Runnable {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Figure.class);

    /**
     * Field.
     */
    private final Field field;

    /**
     * Point.
     */
    private Point point;

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
     *
     * @param point start point.
     * @param field field.
     */
    public Figure(final Point point, final Field field) {
        this.point = point;
        this.field = field;
        this.random = new Random();
        this.move = 4;
    }

    @Override
    public void run() {
        while (!this.field.getCell(this.point).getLock().tryLock()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted: ", e);
                return;
            }
        }
        LOGGER.info(String.format("%s point - %s take block", Thread.currentThread().getName(), this.getPoint()));
        while (!Thread.currentThread().isInterrupted()) {
            Point point = this.getMovePoint(this.random.nextInt(this.move));
            Cell cell = this.field.getCell(point);
            if (cell != null && cell.getLock().tryLock()) {
                LOGGER.info(String.format("%s point - %s take block", Thread.currentThread().getName(), cell.getPoint()));
                Cell oldCell = this.field.getCell(this.getPoint());
                try {
                    oldCell.getLock().unlock();
                    LOGGER.info(String.format("%s point - %s release block", Thread.currentThread().getName(), oldCell.getPoint()));
                } catch (IllegalMonitorStateException e) {
                    LOGGER.error("IllegalMonitorStateException: ", e);
                }
                this.setPoint(point);
                try {
                    Thread.sleep(new Random().nextInt(500));
                } catch (InterruptedException e) {
                    LOGGER.error("interrupted: ", e);
                    return;
                }
            }
        }
    }

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
     * Set.
     *
     * @param point new point.
     */
    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * Randomly moves one cell.
     *
     * @param value variant of the move.
     * @return point.
     */
    private Point getMovePoint(final int value) {
        Point point = null;
        switch (value) {
            case 0:
                point = new Point(this.point.getX() + 1, this.point.getY());
                break;
            case 1:
                point = new Point(this.point.getX() - 1, this.point.getY());
                break;
            case 2:
                point = new Point(this.point.getX(), this.point.getY() + 1);
                break;
            case 3:
                point = new Point(this.point.getX(), this.point.getY() - 1);
                break;
            default:
                break;
        }
        return point;
    }
}
