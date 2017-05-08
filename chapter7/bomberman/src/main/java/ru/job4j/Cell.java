package ru.job4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Cell.
 *
 * @author Alexey Voronin.
 * @since 07.05.2017.
 */
public class Cell {

    /**
     * x and y coordinate.
     */
    private final Point point;

    /**
     * Lock.
     */
    private final Lock lock;

    /**
     * Constructor.
     *
     * @param point point.
     */
    public Cell(final Point point) {
        this.point = point;
        this.lock = new ReentrantLock();
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
     * @return lock.
     */
    public Lock getLock() {
        return this.lock;
    }

    @Override
    public String toString() {
        return String.format("%s.%s", this.getPoint().getX(), this.getPoint().getY());
    }
}
