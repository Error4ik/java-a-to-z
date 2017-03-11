package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for a two-dimensional array.
 *
 * @author Alexey Voronin.
 * @since 11.03.2017.
 */
public class IteratorArraysTwo implements Iterator<Integer> {

    /**
     * Input array.
     */
    private final int[][] array;

    /**
     * First index.
     */
    private int oneIndex = 0;

    /**
     * Second index.
     */
    private int twoIndex = 0;

    /**
     * Constructor.
     *
     * @param array input array.
     */
    public IteratorArraysTwo(final int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean flag = true;
        if (twoIndex >= this.array[1].length) {
            this.twoIndex = 0;
            this.oneIndex++;
        }
        if (this.oneIndex >= this.array.length) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more items!");
        }
        return this.array[oneIndex][twoIndex++];
    }
}
