package ru.job4j.counter_test;

import ru.job4j.counter.Counter;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса Counter.
 */
public class CounterTest {

    /**
     * Тест для метода который возвращает сумму всех четных чисел в диапазоне от first до second.
     */
    @Test
    public void testAdd() {
        Counter counter = new Counter();
        final int first = 1;
        final int second = 10;
        final int result = 30;
        assertThat(result, is(counter.add(first, second)));
    }
}