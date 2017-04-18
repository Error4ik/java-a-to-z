package ru.job4j;

/**
 * Class Counts the number of word in a string.
 *
 * @author Alexey Voronin.
 * @since 12.04.2017.
 */
public class NumberOfWord implements Runnable {

    /**
     * The line in which you want to count.
     */
    private final String data;

    /**
     * Constructor.
     *
     * @param data The line in which you want to count.
     */
    public NumberOfWord(final String data) {
        this.data = data;
    }

    @Override
    public void run() {
        System.out.printf("%s - start!%s", Thread.currentThread().getName(), System.getProperty("line.separator"));
        System.out.printf("%s is running Number of word: %s%s",
                Thread.currentThread().getName(), this.data.split(" +").length, System.getProperty("line.separator"));
        System.out.printf("%s - finish!%s", Thread.currentThread().getName(), System.getProperty("line.separator"));
    }
}
