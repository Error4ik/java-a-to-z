package ru.job4j;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Monster.
 *
 * @author Alexey Voronin.
 * @since 09.05.2017.
 */
public class Monster extends Figure implements Runnable {

    /**
     * Random.
     */
    private final Random random;

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Monster.class);


    /**
     * Variants of the MOVE.
     */
    private static final int MOVE = 4;

    /**
     * Constructor.
     *
     * @param field field.
     */
    public Monster(final Field field) {
        super(field);
        this.random = new Random();
    }

    @Override
    public void run() {
        this.spawnPoint();
        while (!Thread.currentThread().isInterrupted()) {
            this.moveFigure(this.getMovePoint(random.nextInt(MOVE)));
            try {
                Thread.sleep(this.random.nextInt(500));
            } catch (InterruptedException e) {
                LOGGER.error("interrupted: ", e);
                return;
            }
        }
    }

    @Override
    void moveFigure(final Point point) {
        Cell cell = this.getField().getCell(point);
        if (cell != null && cell.getLock().tryLock()) {
            LOGGER.info(String.format("%s point - %s take block", Thread.currentThread().getName(), cell.getPoint()));
            Cell oldCell = this.getField().getCell(this.getPoint());
            try {
                this.setPoint(point);
            } finally {
                oldCell.getLock().unlock();
                LOGGER.info(String.format("%s point - %s release block", Thread.currentThread().getName(), oldCell.getPoint()));
            }
        }
    }

    /**
     * Randomly sets the monster on the field.
     */
    private void spawnPoint() {
        this.setPoint(new Point(random.nextInt(this.getField().getRow()),
                random.nextInt(this.getField().getColumn())));

        while (!this.getField().getCell(this.getPoint()).getLock().tryLock()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
            Point point = this.getMovePoint(this.random.nextInt(MOVE));
            if (this.getField().checkPoint(point)) {
                this.setPoint(point);
            }
        }
        LOGGER.info(String.format("%s point - %s take block", Thread.currentThread().getName(), this.getPoint()));
    }

    /**
     * Randomly moves one cell.
     *
     * @param value variant of the MOVE.
     * @return point.
     */
    private Point getMovePoint(final int value) {
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
