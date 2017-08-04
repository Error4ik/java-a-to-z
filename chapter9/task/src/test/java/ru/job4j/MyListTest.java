package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * My list test.
 *
 * @author Alexey Voronin.
 * @since 04.08.2017.
 */
public class MyListTest {

    /**
     * Add value test.
     */
    @Test
    public void whenAddingNewValueToTheListItIsAdded() {
        final MyList<Integer> list = new MyList<>();
        final String expectedValue = "1 2 3 4 5";

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertThat(list.toString(), is(expectedValue));
    }

    /**
     * Reverse test.
     */
    @Test
    public void whenReverseShouldInReverseOrder() {
        final MyList<Integer> list = new MyList<>();
        final String expectedValue = "5 4 3 2 1";

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.reverse();
        assertThat(list.toString(), is(expectedValue));
    }

    /**
     * Reverse test.
     */
    @Test
    public void whenReverseShouldInReverseOrderByString() {
        final MyList<String> list = new MyList<>();
        final String expectedValue = "e d c b a";

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.reverse();
        assertThat(list.toString(), is(expectedValue));
    }
}