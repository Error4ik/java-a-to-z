package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Even iterator.
 *
 * @author Alexey Voronin.
 * @since 07.03.2017.
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    /**
     * Input array.
     */
    private final int[] array;

    /**
     * Index.
     */
    private int index = 0;

    /**
     * Constructor.
     *
     * @param array input array.
     */
    public EvenNumbersIterator(final int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean flag = false;
        for (int i = index; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                index = i;
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("No more items.");
        }
        return this.array[index++];
    }
}
