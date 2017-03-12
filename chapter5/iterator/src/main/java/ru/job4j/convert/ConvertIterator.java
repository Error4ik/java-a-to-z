package ru.job4j.convert;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Convert Iterator Bypasses nested iterators.
 *
 * @author Alexey Voronin.
 * @since 11.03.2017.
 */
public class ConvertIterator implements IConvert {

    /**
     * Iterator of iterator.
     */
    private Iterator<Iterator<Integer>> iteratorIterator;

    /**
     * Current iterator.
     */
    private Iterator<Integer> currentIterator;

    /**
     * Convert Take iterator of iterator and return iterator of numbers.
     *
     * @param iteratorIterator iterator of iterator.
     * @return iterator to numbers.
     */
    @Override
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iteratorIterator) {
        this.iteratorIterator = iteratorIterator;
        return this;
    }

    @Override
    public boolean hasNext() {
        boolean flag = false;
        if (currentIterator == null && iteratorIterator.hasNext()) {
            currentIterator = iteratorIterator.next();
            flag = true;
        } else if (!currentIterator.hasNext() && iteratorIterator.hasNext()) {
            currentIterator = iteratorIterator.next();
            flag = true;
        } else {
            flag = this.currentIterator.hasNext();
        }

        return flag;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more items!");
        }
        return currentIterator.next();
    }
}
