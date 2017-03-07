package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array iterator.
 *
 * @author Alexey Voronin.
 * @since 07.03.2017.
 */
public class ArrayIterator implements Iterator<Integer> {

    /**
     * Array int value.
     */
    private final int[] array;

    /**
     * Index array.
     */
    private int index = 0;

    /**
     * Constructor.
     *
     * @param array input array.
     */
    public ArrayIterator(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return this.array.length > index;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("No more items.");
        }
        return this.array[index++];
    }

}
