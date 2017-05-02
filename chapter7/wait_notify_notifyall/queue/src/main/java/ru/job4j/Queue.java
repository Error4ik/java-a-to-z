package ru.job4j;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * Blocking queue.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 30.04.2017.
 */
public class Queue<T> {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Queue.class);

    /**
     * Blocking queue.
     */
    private final List<T> blockingQueue;

    /**
     * Max size queue.
     */
    private final int maxSize;

    /**
     * Signal to the producer and the consumer when to work, and when to wait.
     */
    private volatile boolean flag;

    /**
     * Constructor.
     *
     * @param capacity size to queue.
     */
    public Queue(final int capacity) {
        this.blockingQueue = new LinkedList<T>();
        this.maxSize = capacity;
        this.flag = false;
    }

    /**
     * Put value to queue.
     *
     * @param value value.
     */
    public synchronized void put(final T value) {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted: ", e);
            }
        }

        this.blockingQueue.add(value);
        LOGGER.info(String.format("Producer %s put value: %s", Thread.currentThread().getName(), value));

        if (this.blockingQueue.size() == this.maxSize) {
            LOGGER.info("Producer say notifyAll!");
            LOGGER.info("Queue is full, producer wait!");
            notifyAll();
            flag = true;
        }
    }

    /**
     * Take value from queue.
     *
     * @return value.
     */
    public synchronized T take() {
        while (!flag) {
            try {
                LOGGER.info("Queue is empty, consumer wait! " + Thread.currentThread().getName());
                wait();
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted: ", e);
            }
        }

        T value = this.blockingQueue.remove(0);
        LOGGER.info(String.format("Consumer %s take value: %s", Thread.currentThread().getName(), value));

        if (this.blockingQueue.size() == 0) {
            LOGGER.info("Consumer say notifyAll!");
            notifyAll();
            flag = false;
        }
        return value;
    }
}
