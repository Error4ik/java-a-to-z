package ru.job4j.counter_test;

import ru.job4j.counter.Counter;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ���� ������ Counter.
 */
public class CounterTest {

    /**
     * ���� ��� ������ ������� ���������� ����� ���� ������ ����� � ��������� �� first �� second.
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