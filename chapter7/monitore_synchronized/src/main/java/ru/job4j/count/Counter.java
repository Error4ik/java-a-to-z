package ru.job4j.count;

import org.apache.log4j.Logger;

/**
 * Counter.
 *
 * @author Alexey Voronin.
 * @since 25.04.2017.
 */
public class Counter {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Counter.class);

    /**
     * increment field.
     */
    private int count;

    /**
     * Constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Method increment field.
     */
    public synchronized void increment() {
        this.count++;
    }

    /**
     * Get.
     *
     * @return count.
     */
    public int getCount() {
        return this.count;
    }

    /**
     * Main method.
     * Starts 2 threads in which the method of increasing the variable is called.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        LOGGER.info("Start program.");
        Counter count = new Counter();
        Thread first = new Thread(new MyThread(count));
        first.start();
        Thread second = new Thread(new MyThread(count));
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info(String.format("Counter value: %s", count.getCount()));
        LOGGER.info("finish program.");
    }
}
