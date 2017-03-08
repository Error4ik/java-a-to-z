package ru.job4j;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test PrimesIterator.
 *
 * @author Alexey Voronin.
 * @since 08.03.2017.
 */
public class PrimesIteratorTest {

    /**
     * When array contains primes.
     */
    @Test
    public void whenTakeArrayToNumbersShouldReturnPrimes() {
        final int[] inputArray = new int[]{1, 4, 3, 6, 19};
        final int expectedValue1 = 3;
        final int expectedValue2 = 19;

        final PrimesIterator iterator = new PrimesIterator(inputArray);

        assertThat(iterator.next(), is(expectedValue1));
        assertThat(iterator.next(), is(expectedValue2));
    }

    /**
     * When array not contains primes, throws NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenArrayNotContainsPrimeNumberShouldThrowsException() {
        final int[] inputArray = new int[]{1, 4, 8, 6, 35};

        final PrimesIterator iterator = new PrimesIterator(inputArray);
        iterator.next();
    }

}