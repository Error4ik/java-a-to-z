package ru.job4j;

import org.junit.Test;


import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test EvenNumbersIterator.
 *
 * @author Alexey Voronin.
 * @since 07.03.2017.m
 */
public class EvenNumbersIteratorTest {

    /**
     * Return only even numbers.
     */
    @Test
    public void whenTakeArrayToNumbersShouldReturnEvenNumbers() {
        final EvenNumbersIterator iterator = new EvenNumbersIterator(new int[]{11, 4, 7, 6, 9, 8, 15, 24});
        final int expectedValue1 = 4;
        final int expectedValue2 = 6;
        final int expectedValue3 = 8;
        final int expectedValue4 = 24;

        assertThat(iterator.next(), is(expectedValue1));
        assertThat(iterator.next(), is(expectedValue2));
        assertThat(iterator.next(), is(expectedValue3));
        assertThat(iterator.next(), is(expectedValue4));
    }
}