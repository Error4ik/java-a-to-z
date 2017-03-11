package ru.job4j.convert;

import java.util.Iterator;

/**
 * Interface convert.
 *
 * @author Alexey Voronin.
 * @since 11.03.2017.
 */
public interface IConvert extends Iterator<Integer> {

    /**
     * Convert Take iterator of iterator and return iterator of numbers.
     *
     * @param iteratorIterator iterator of iterator.
     * @return iterator to numbers.
     */
    Iterator<Integer> convert(final Iterator<Iterator<Integer>> iteratorIterator);
}
