package ru.job4j.convert;

import java.util.Iterator;

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
        if (iteratorIterator.hasNext()) {
            flag = true;
        } else if (currentIterator != null && currentIterator.hasNext()) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Integer next() {
        if (currentIterator == null) {
            currentIterator = iteratorIterator.next();
        } else if (!currentIterator.hasNext()) {
            currentIterator = iteratorIterator.next();
        }
        return currentIterator.next();
    }
}
