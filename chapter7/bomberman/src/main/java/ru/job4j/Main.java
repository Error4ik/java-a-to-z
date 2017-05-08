package ru.job4j;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Runtime.getRuntime;

/**
 * Main.
 *
 * @author Alexey Voronin.
 * @since 07.05.2017.
 */
public class Main {

    /**
     * Main method.
     * @param args args.
     */
    public static void main(String[] args) {
        Field field = new Field(10, 10);

        ExecutorService service = Executors.newFixedThreadPool(getRuntime().availableProcessors());

        Thread thread =  new Thread(new Figure(new Point(0, 0), field));
        Thread thread1 = new Thread(new Figure(new Point(0, 0), field));
        Thread thread2 = new Thread(new Figure(new Point(0, 0), field));
        Thread thread3 = new Thread(new Figure(new Point(0, 0), field));
        service.execute(thread);
        service.execute(thread1);
        service.execute(thread2);
        service.execute(thread3);

        try {
            Thread.sleep(10000);
            service.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
