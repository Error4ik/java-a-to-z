package ru.job4j.user_storage;

import org.apache.log4j.Logger;

/**
 * Start.
 *
 * @author Alexey Voronin.
 * @since 25.04.2017.
 */
public class Start {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Start.class);

    /**
     * Creates 2 threads and starts the transaction.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        UserStorage storage = new UserStorage();
        User one = new User("Garry");
        User two = new User("Alex");

        storage.addUser(one);
        storage.addUser(two);

        LOGGER.info("Before the transaction begins.");
        storage.addMoney(one, 3_000_000);
        LOGGER.info(one);
        LOGGER.info(two);
        LOGGER.info("Start transaction!");
        Thread first = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; i++) {
                    storage.transaction(one, 10, two);
                }
            }
        });
        first.start();

        Thread second = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100_000; i++) {
                    storage.transaction(one, 10, two);
                }
            }
        });
        second.start();

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("End Transaction!");
        LOGGER.info(one);
        LOGGER.info(two);
    }
}
