package ru.job4j;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Producer.
 *
 * @author Alexey Voronin.
 * @since 30.04.2017.
 */
public class Producer implements Runnable {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Producer.class);

    /**
     * Blocking queue.
     */
    private final Queue<Integer> queue;

    /**
     * Random generator.
     */
    private final Random random;

    /**
     * Constructor.
     *
     * @param queue blocking queue.
     */
    public Producer(final Queue<Integer> queue) {
        this.queue = queue;
        this.random = new Random();
    }

    @Override
    public void run() {
        LOGGER.info("Producer start");
        while (true) {
            try {
                Thread.sleep(2000);
                this.produce();
            } catch (InterruptedException e) {
                LOGGER.error("Interrupt: ", e);
            }
        }
    }

    /**
     * Put value to queue.
     */
    private void produce() {
        this.queue.put(this.random.nextInt(500));
    }
}
