package ru.job4j;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Alexey Voronin.
 * @since 07.03.2017.
 */
public class ArrayIteratorTest {

    /**
     * Input array.
     */
    private final int[] inputArray = new int[]{1, 5, 2};

    /**
     * Method next.
     * If method is called first time, should return first array item (1).
     */
    @Test
    public void whenMethodNextIsCalledAFirstTimeShouldReturnFirstArrayItem() {
        final int expectedValue = 1;
        final Iterator<Integer> iterator = new ArrayIterator(inputArray);

        assertThat(iterator.next(), is(expectedValue));
    }

    /**
     * Method next.
     * If method is called second time, should return second array item (5).
     */
    @Test
    public void whenMethodNextIsCalledSecondTimeShouldReturnSecondArrayItem() {
        final int expectedVale = 5;
        final Iterator<Integer> iterator = new ArrayIterator(inputArray);

        iterator.next();

        assertThat(iterator.next(), is(expectedVale));
    }

    /**
     * Method next.
     * If you call a method when there are no more elements should throw exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCallMethodWhenThereAreNoMoreItemShouldThrowException() {
        final Iterator<Integer> iterator = new ArrayIterator(inputArray);

        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    /**
     * Method hasNext.
     * Return true if next item exist.
     */
    @Test
    public void whenNextItemExistShouldReturnTrue() {
        final Iterator<Integer> iterator = new ArrayIterator(inputArray);
        final boolean expectedValue = true;

        assertThat(iterator.hasNext(), is(expectedValue));
    }

    /**
     * Method hasNext.
     * Return false if next item not exist.
     */
    @Test
    public void whenNextItemNotExistShouldReturnFalse() {
        final Iterator<Integer> iterator = new ArrayIterator(inputArray);
        final boolean expectedValue = false;

        iterator.next();
        iterator.next();
        iterator.next();

        assertThat(iterator.hasNext(), is(expectedValue));
    }


}