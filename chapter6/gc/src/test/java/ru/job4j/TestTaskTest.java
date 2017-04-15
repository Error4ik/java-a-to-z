package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Alexey Voronin.
 * @since 15.04.2017.
 */
public class TestTaskTest {

    /**
     * Method unwrap.
     * Test 3x3 array.
     */
    @Test
    public void unwrapThreeByThreeMatrix() {
        final TestTask testTask = new TestTask();
        final int[] expectedValue = new int[]{1, 2, 3, 6, 9, 8, 7, 4, 5};

        final int[] actualValue = testTask.unwrap(this.fillArray(3));

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Method unwrap.
     * Test 5x5 array.
     */
    @Test
    public void unwrapFiveByFiveMatrix() {
        final TestTask testTask = new TestTask();
        final int[] expectedValue = new int[]{
                1,  2,  3,  4,  5,
                10, 15, 20, 25, 24,
                23, 22, 21, 16, 11,
                6,  7,  8,  9,  14,
                19, 18, 17, 12, 13
        };

        final int[] actualValue = testTask.unwrap(this.fillArray(5));

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Fill array.
     * @param size size to array.
     * @return filled array.
     */
    private int[][] fillArray(final int size) {
        final int[][] returnArray = new int[size][size];
        int number = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                returnArray[i][j] = number++;
            }
        }
        return returnArray;
    }
}