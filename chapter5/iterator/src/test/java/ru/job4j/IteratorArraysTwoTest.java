package ru.job4j;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Iterator Test.
 *
 * @author Alexey Voronin.
 * @since 11.03.2017.
 */
public class IteratorArraysTwoTest {

    /**
     * Method next.
     * If iterator take array {{1, 2}, {3, 4}} should method next return 1, 2, 3, 4.
     */
    @Test
    public void whenTakeArrayShouldMethodNextReturnTheItemsInTurn() {
        final int[][] inputValue = new int[][]{{1, 2}, {3, 4}};
        final IteratorArraysTwo iterator = new IteratorArraysTwo(inputValue);
        final int expectedFirst = 1;
        final int expectedSecond = 2;
        final int expectedThird = 3;
        final int expectedFour = 4;

        assertThat(iterator.next(), is(expectedFirst));
        assertThat(iterator.next(), is(expectedSecond));
        assertThat(iterator.next(), is(expectedThird));
        assertThat(iterator.next(), is(expectedFour));
    }

    /**
     * Method next.
     * If iterator take array {{4, 3}, {2, 1}} should method next return 4, 3, 2, 1.
     */
    @Test
    public void whenTakeArrayShouldMethodNextReturnValidTheItemsInTurn() {
        final int[][] inputValue = new int[][]{{4, 3}, {2, 1}};
        final IteratorArraysTwo iterator = new IteratorArraysTwo(inputValue);
        final int expectedFirst = 4;
        final int expectedSecond = 3;
        final int expectedThird = 2;
        final int expectedFour = 1;

        assertThat(iterator.next(), is(expectedFirst));
        assertThat(iterator.next(), is(expectedSecond));
        assertThat(iterator.next(), is(expectedThird));
        assertThat(iterator.next(), is(expectedFour));
    }

    /**
     * Method next.
     * If not more item throws NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCallMethodWhenThereAreNoMoreItemShouldThrowException() {
        final int[][] inputValue = new int[][]{{1}, {3}};
        final IteratorArraysTwo iterator = new IteratorArraysTwo(inputValue);

        iterator.next();
        iterator.next();
        iterator.next();
    }
}