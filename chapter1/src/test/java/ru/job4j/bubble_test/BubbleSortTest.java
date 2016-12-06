package ru.job4j.bubble_test;

import ru.job4j.bubble.BubbleSort;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса сортировки массива.
 */
public class BubbleSortTest {

    /**
     * тест для пузырьковой сортировки массива.
     */
    @Test
    public void testBubbleSort() {
        final BubbleSort bubbleSort = new BubbleSort();
        final int[] actualArray = new int[]{3, 9, 1, 8, 6, 22};
        final int[] expectedArray = new int[] {1, 3, 6, 8, 9, 22};
        assertThat(expectedArray, is(bubbleSort.bubbleSort(actualArray)));
    }
}