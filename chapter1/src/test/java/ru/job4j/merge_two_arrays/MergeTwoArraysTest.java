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
        final int[] first = new int[] {1, 2, 3, 4, 5};
        final int[] second = new int[] {5, 4, 3, 2, 1};
        MergeTwoArrays merge = new MergeTwoArrays();
        final int[] expected = new int[] {1, 2, 3, 4, 5, 5, 4, 3, 2, 1};
        assertThat(merge.mergeTwoArrays(first, second), is(expected));
    }

    /**
     * Тест объеденения 2 массивов с помощью System.arraycopy.
     */
    @Test
    public void mergeTwoArraysUseSystemArrayCopy() {
        final int[] first = new int[] {1, 2, 3, 4, 5};
        final int[] second = new int[] {5, 4, 3, 2, 1};
        MergeTwoArrays merge = new MergeTwoArrays();
        final int[] expected = new int[] {1, 2, 3, 4, 5, 5, 4, 3, 2, 1};
        assertThat(merge.mergeTwoArrays2(first, second), is(expected));
    }
}