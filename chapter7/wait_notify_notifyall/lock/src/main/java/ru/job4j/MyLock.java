package ru.job4j;

/**
 * My lock.
 *
 * @author Alexey Voronin.
 * @since 02.05.2017.
 */
public class MyLock implements Lock {

    /**
     * If true - lock if the lie is - unlocked.
     */
    private boolean isLocked;

    @Override
    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isLocked = true;
    }

    @Override
    public synchronized void unlock() {
        this.isLocked = false;
        notifyAll();
    }
}
