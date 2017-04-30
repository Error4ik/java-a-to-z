package ru.job4j;

/**
 * Start Producer Consumer.
 *
 * @author Alexey Voronin.
 * @since 30.04.2017.
 */
public class StartProducerConsumer {

    /**
     * Main method, start Producer and Consumer.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        final Queue<Integer> queue = new Queue<>(5);
        Thread producer = new Thread(new Producer(queue));
        producer.start();
        Thread consumer = new Thread(new Consumer(queue));
        consumer.start();
    }
}
