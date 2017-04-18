package ru.job4j;

/**
 * Class counts the number of spaces in a string.
 *
 * @author Alexey Voronin.
 * @since 12.04.2017.
 */
public class NumberOfSpace implements Runnable {

    /**
     * The line in which you want to count.
     */
    private final String data;

    /**
     * Constructor.
     *
     * @param data The line in which you want to count.
     */
    public NumberOfSpace(final String data) {
        this.data = data;
    }

    @Override
    public void run() {
        System.out.printf("%s - start!%s", Thread.currentThread().getName(), System.getProperty("line.separator"));
        int number = 0;
        for (char c : this.data.toCharArray()) {
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
            if (c == ' ') {
                number++;
            }
        }
        System.out.printf("%s is running Number of space: %s%s",
                Thread.currentThread().getName(), number, System.getProperty("line.separator"));
        System.out.printf("%s - finish!%s", Thread.currentThread().getName(), System.getProperty("line.separator"));
    }
}
