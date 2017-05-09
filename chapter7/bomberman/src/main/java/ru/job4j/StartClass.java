package ru.job4j;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Start.
 *
 * @author Alexey Voronin.
 * @since 09.05.2017.
 */
public class StartClass {

    /**
     * Number of monsters.
     */
    private final int numberMonsters;

    /**
     * Threads.
     */
    private final Thread[] threads;

    /**
     * Field.
     */
    private final Field field;

    /**
     * Constructor.
     *
     * @param row            row.
     * @param column         column.
     * @param numberMonsters number of monsters.
     */
    public StartClass(final int row, final int column, final int numberMonsters) {
        this.field = new Field(row, column);
        this.numberMonsters = numberMonsters;
        this.threads = new Thread[this.numberMonsters];
    }

    /**
     * add threads to array.
     */
    public void addThreads() {
        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i] = new Thread(new Monster(this.field));
        }
    }

    /**
     * Main method.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        StartClass start = new StartClass(10, 10, 4);
        start.addThreads();
        ExecutorService service = Executors.newFixedThreadPool(start.numberMonsters);

        for (Thread thread : start.threads) {
            service.execute(thread);
        }

        try {
            Thread.sleep(10000);
            service.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
