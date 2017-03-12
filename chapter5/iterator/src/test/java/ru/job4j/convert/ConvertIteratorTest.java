package ru.job4j.convert;

import org.junit.Test;
import ru.job4j.ArrayIterator;


import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ConvertIteratorTest.
 *
 * @author Alexey Voronin.
 * @since 11.03.2017.
 */
public class ConvertIteratorTest {

    /**
     * Test convert iterator.
     */
    @Test
    public void whenIteratorIteratorCallMethodNextShouldReturnIteratorToNumbers() {
        final Iterator<Integer> iter1 = new ArrayIterator(new int[]{1, 2, 3});
        final Iterator<Integer> iter2 = new ArrayIterator(new int[]{4, 5, 6});
        final Iterator<Integer> iter3 = new ArrayIterator(new int[]{7, 8, 9});
        final List<Integer> expectedList = new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
        final Iterator<Iterator<Integer>> iteratorIterator = Arrays.asList(iter1, iter2, iter3).iterator();

        final ConvertIterator convertIterator = new ConvertIterator();
        final Iterator<Integer> iterator = convertIterator.convert(iteratorIterator);

        final List<Integer> actualValue = new ArrayList<>();
        while (iterator.hasNext()) {
            actualValue.add(iterator.next());
        }

        assertThat(actualValue, is(expectedList));
    }

    /**
     * Test convert iterator.
     * Add new test of other values.
     */
    @Test
    public void whenIteratorIteratorTakeArraysDifferentSizesShouldReturnIteratorToNumbers() {
        final Iterator<Integer> iter1 = new ArrayIterator(new int[]{1, 8, 3});
        final Iterator<Integer> iter2 = new ArrayIterator(new int[]{16, 5});
        final Iterator<Integer> iter3 = new ArrayIterator(new int[]{1, 12, 4});
        final List<Integer> expectedList = new ArrayList<Integer>(Arrays.asList(new Integer[]{1, 8, 3, 16, 5, 1, 12, 4}));
        final Iterator<Iterator<Integer>> iteratorIterator = Arrays.asList(iter1, iter2, iter3).iterator();

        final ConvertIterator convertIterator = new ConvertIterator();
        final Iterator<Integer> iterator = convertIterator.convert(iteratorIterator);

        final List<Integer> actualValue = new ArrayList<>();
        while (iterator.hasNext()) {
            actualValue.add(iterator.next());
        }

        assertThat(actualValue, is(expectedList));
    }

    /**
     * Test convert iterator.
     * Method next, if no more items throws exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenNoMoreItemsShouldThrowsException() {
        final Iterator<Integer> iter1 = new ArrayIterator(new int[]{1, 8, 3});
        final Iterator<Integer> iter2 = new ArrayIterator(new int[]{16, 5});
        final Iterator<Integer> iter3 = new ArrayIterator(new int[]{1, 12, 4});
        final Iterator<Iterator<Integer>> iteratorIterator = Arrays.asList(iter1, iter2, iter3).iterator();

        final ConvertIterator convertIterator = new ConvertIterator();
        final Iterator<Integer> iterator = convertIterator.convert(iteratorIterator);

        for (int i = 0; i < 10; i++) {
            iterator.next();
        }
    }
}
