package ru.job4j;

import org.apache.log4j.Logger;

/**
 * Threads problem.
 *
 * @author Alexey Voronin.
 * @since 20.04.2017.
 */
public class ThreadsProblem {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ThreadsProblem.class);

    /**
     * The field for the increment.
     */
    private int number = 0;

    /**
     * Increment method.
     */
    public void increment() {
        for (int i = 0; i < 1_000_000; i++) {
            this.number++;
        }
    }

    /**
     * Get.
     *
     * @return number.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Main method.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        final ThreadsProblem problem = new ThreadsProblem();

        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("First thread started.");
                problem.increment();
                LOGGER.info("The first thread ended.");
            }
        });
        first.start();
        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("Second thread started.");
                problem.increment();
                LOGGER.info("The second thread ended.");
            }
        });
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException: ", e);
        }
        LOGGER.info(String.format("The value of a number: %s", problem.getNumber()));
    }
}
