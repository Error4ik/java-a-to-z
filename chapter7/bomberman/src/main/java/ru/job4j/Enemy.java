package ru.job4j;

import org.apache.log4j.Logger;

/**
 * Any figure.
 *
 * @author Alexey Voronin.
 * @since 07.05.2017.
 */
public class Enemy extends AFigure {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Enemy.class);


    /**
     * Constructor.
     *
     * @param field start point.
     * @param point field.
     */
    public Enemy(final Field field, final Point point) {
        super(field, point, 4);
    }

    @Override
    public void run() {
        while (!this.getField().getCell(this.getPoint()).getLock().tryLock()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted: ", e);
                return;
            }
        }
        LOGGER.info(String.format("%s point - %s take block", Thread.currentThread().getName(), this.getPoint()));
        while (!Thread.currentThread().isInterrupted()) {
            Point point = this.getMovePoint(this.getRandom().nextInt(this.getMove()));
            Cell cell = this.getField().getCell(point);
            if (cell != null && cell.getLock().tryLock()) {
                LOGGER.info(String.format("%s point - %s take block", Thread.currentThread().getName(), cell.getPoint()));
                Cell oldCell = this.getField().getCell(this.getPoint());
                try {
                    oldCell.getLock().unlock();
                    LOGGER.info(String.format("%s point - %s release block", Thread.currentThread().getName(), oldCell.getPoint()));
                } catch (IllegalMonitorStateException e) {
                    LOGGER.error("IllegalMonitorStateException: ", e);
                }
                this.setPoint(point);
                try {
                    Thread.sleep(this.getRandom().nextInt(500));
                } catch (InterruptedException e) {
                    LOGGER.error("interrupted: ", e);
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "[" + this.getPoint().getX() + ":" + this.getPoint().getY() + "]";
    }

    /**
     * Randomly moves one cell.
     *
     * @param value variant of the move.
     * @return point.
     */
    public Point getMovePoint(final int value) {
        Point point = null;
        switch (value) {
            case 0:
                point = new Point(this.getPoint().getX() + 1, this.getPoint().getY());
                break;
            case 1:
                point = new Point(this.getPoint().getX() - 1, this.getPoint().getY());
                break;
            case 2:
                point = new Point(this.getPoint().getX(), this.getPoint().getY() + 1);
                break;
            case 3:
                point = new Point(this.getPoint().getX(), this.getPoint().getY() - 1);
                break;
            default:
                break;
        }
        return point;
    }
}
