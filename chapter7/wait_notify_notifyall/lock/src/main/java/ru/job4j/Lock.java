package ru.job4j;

/**
 * Lock.
 *
 * @author Alexey Voronin.
 * @since 02.05.2017.
 */
public interface Lock {

    /**
     * If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until the
     * lock has been acquired.
     */
    void lock();

    /**
     * Releases the lock.
     */
    void unlock();
}
