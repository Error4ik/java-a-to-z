package ru.job4j.additional_task;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса FindTheMaximumDifference.
 */
public class FindTheMaximumDifferenceTest {

    /**
     * Тест поиска маклималоной разницы индексов одинаковых чисел в массиве.
     */
    @Test
    public void findNumber() {
        final int[] inputArray = {1, 9, 22, 1, 12, 6, 11, 8, 1, 3};
        final int expectedValue = 8;
        FindTheMaximumDifference find = new FindTheMaximumDifference();

        assertThat(find.findNumber(inputArray), is(expectedValue));
    }
}