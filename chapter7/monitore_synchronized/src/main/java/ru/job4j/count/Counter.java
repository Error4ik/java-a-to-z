package ru.job4j.count;

import org.apache.log4j.Logger;

/**
 * Counter.
 *
 * @author Alexey Voronin.
 * @since 25.04.2017.
 */
public class Counter {

    /**
     * increment field.
     */
    private int count;

    /**
     * Constructor.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * Method increment field.
     */
    public synchronized void increment() {
        this.count++;
    }

    /**
     * Get.
     *
     * @return count.
     */
    public int getCount() {
        return this.count;
    }
}
