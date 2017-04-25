package ru.job4j.count;

import org.apache.log4j.Logger;

/**
 * My Thread.
 *
 * @author Alexey Voronin.
 * @since 25.04.2017.
 */
public class MyThread implements Runnable {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(MyThread.class);

    /**
     * Counter.
     */
    private final Counter counter;

    /**
     * Constructor.
     * @param counter counter.
     */
    public MyThread(final Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        LOGGER.info(String.format("Thread %s start.", Thread.currentThread().getName()));
        for (int i = 0; i < 2_000_000; i++) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            counter.increment();
        }
        LOGGER.info(String.format("Thread %s finish.", Thread.currentThread().getName()));
    }
}
