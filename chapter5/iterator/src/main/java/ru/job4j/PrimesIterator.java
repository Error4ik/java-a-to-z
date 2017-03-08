package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class return only primes numbers.
 *
 * @author Alexey Voronin.
 * @since 07.03.2017.
 */
public class PrimesIterator implements Iterator<Integer> {

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
    public PrimesIterator(final int[] array) {
        this.array = array;
    }

    /**
     * @return true if to array there is primes.
     */
    @Override
    public boolean hasNext() {
        boolean flag = false;
        for (int i = this.index; i < this.array.length; i++) {
            int tmp = this.array[i];
            if (tmp < 2 || tmp != 2 && tmp % 2 == 0) {
                continue;
            } else {
                flag = true;
            }

            int divide = 3;
            while (divide * divide <= tmp) {
                if (tmp % divide == 0) {
                    flag = false;
                    break;
                }
                divide += 2;
            }

            if (flag) {
                this.index = i;
                break;
            }
        }
        return this.index < this.array.length && flag;
    }

    /**
     * @return primes.
     */
    @Override
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException("No more items!");
        }
        return this.array[this.index++];
    }
}
