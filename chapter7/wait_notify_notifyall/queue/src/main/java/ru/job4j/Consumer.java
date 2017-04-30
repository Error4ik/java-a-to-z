package ru.job4j;

import org.apache.log4j.Logger;

/**
 * Consumer.
 *
 * @author Alexey Voronin.
 * @since 30.04.2017.
 */
public class Consumer implements Runnable {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Consumer.class);

    /**
     * Blocking queue.
     */
    private final Queue<Integer> queue;

    /**
     * Constructor.
     * @param queue blocking queue.
     */
    public Consumer(final Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        LOGGER.info("Consumer start");
        while (true) {
            try {
                this.consume();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Consumer take value from queue.
     */
    private void consume() {
        this.queue.take();
    }
}
