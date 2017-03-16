package ru.job4j;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * My Set test.
 *
 * @author Alexey Voronin.
 * @since 15.03.2017.
 */
public class MySetTest {

    /**
     * Method add.
     * If the element that you want to add is not in the set, then the truth is returned.
     */
    @Test
    public void whenItemIsNotInTheSetThenReturnTrue() {
        final MySet<Integer> set = new MySet<>();

        assertTrue(set.add(1));
    }

    /**
     * Method add.
     * If the item you want to add to the set is already there, it returns false.
     */
    @Test
    public void whenSetContainItemThenReturnFalse() {
        final MySet<Integer> set = new MySet<>();

        set.add(1);
        set.add(2);

        assertFalse(set.add(1));
    }

    /**
     * Method add and change capacity.
     * If array is full, then capacity should increment.
     */
    @Test
    public void whenArrayIsFullThenCapacityShouldIncrement() {
        final MySet<Integer> set = new MySet<>(2);
        final int expectedValue = 4;

        set.add(1);
        set.add(2);
        set.add(3);

        assertThat(set.size(), is(expectedValue));
    }

    /**
     * Method addAll.
     * Adds all unique elements from the input array to the current set.
     */
    @Test
    public void whenAddsInputArrayThenAddUniqueItemToCurrentSet() {
        final MySet<Integer> set = new MySet<>();
        final int[] expectedArray = new int[]{1, 2, 3, 4, 5};
        final int[] actualArray = new int[5];

        set.addAll(new Integer[]{1, 2, 2, 1, 3, 4, 5, 4});
        Iterator<Integer> iterator = set.iterator();
        for (int i = 0; i < actualArray.length; i++) {
            actualArray[i] = iterator.next();
        }

        assertThat(actualArray, is(expectedArray));
    }

    /**
     * If there are no items, throws an exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenListIsEmptyAndNoMoreItemsThenThrowsException() {
        final MySet<Integer> set = new MySet<>(1);
        final Iterator<Integer> iterator = set.iterator();

        set.add(1);
        iterator.next();
        iterator.next();
    }
}