package ru.job4j.merge_two_arrays;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса MergeTwoArrays.
 */
public class MergeTwoArraysTest {

    /**
     * Тест объеденения 2 массивов с помощью циклов.
     */
    @Test
    public void mergeTwoArraysUseCycle() {
        final int[] first = new int[] {9, 10, 11, 14, 15, 26, 38};
        final int[] second = new int[] {1, 2, 8, 14, 24};
        final int[] expected = new int[] {1, 2, 8, 9, 10, 11, 14, 14, 15, 24, 26, 38};
        MergeTwoArrays merge = new MergeTwoArrays();
        assertThat(merge.mergeTwoArrays(first, second), is(expected));
    }
}