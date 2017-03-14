package ru.job4j.queue;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * MyQueue test.
 *
 * @author Alexey Voronin.
 * @since 14.03.2017.
 */
public class MyQueueTest {

    /**
     * Test method add and pull.
     */
    @Test
    public void whenRequestAnItemFromTheQueueShouldReturnedFirstItem() {
        final int inputValue1 = 10;
        final int inputValue2 = 20;
        final int inputValue3 = 30;
        final int expectedValue = inputValue1;

        final MyQueue<Integer> queue = new MyQueue<>();

        queue.add(inputValue1);
        queue.add(inputValue2);
        queue.add(inputValue3);

        assertThat(queue.pull(), is(expectedValue));
    }
}