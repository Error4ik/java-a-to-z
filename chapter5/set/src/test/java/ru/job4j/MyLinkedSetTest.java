package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * MyLinkedSet test.
 *
 * @author Alexey Voronin.
 * @since 16.03.2017.
 */
public class MyLinkedSetTest {

    /**
     * Method add.
     * add item to not exist to list.
     */
    @Test
    public void whenItemNotExistToListThenShouldAddItem() {
        final MyLinkedSet<Integer> set = new MyLinkedSet<>();
        final int expectedValue = 5;

        set.add(5);

        assertThat(set.get(0), is(expectedValue));
    }

    /**
     * Method add.
     * If the item is in the list, it will not be added.
     */
    @Test
    public void whenItemIsInTheListItWillNotBeAdded() {
        final MyLinkedSet<Integer> list = new MyLinkedSet<>();
        final int[] expectedValue = new int[]{1, 2, 3};

        list.add(1);
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(3);

        final int[] actualValue = new int[3];
        int index = 0;
        for (Integer integer : list) {
            actualValue[index++] = integer;
        }

        assertThat(actualValue, is(expectedValue));
    }
}