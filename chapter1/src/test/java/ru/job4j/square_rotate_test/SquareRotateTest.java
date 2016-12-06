package ru.job4j.square_rotate_test;

import ru.job4j.square_rotate.SquareRotate;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса SquareRotate.
 */
public class SquareRotateTest {

    /**
     * Тест переворота двумерного массива на 90 градусов.
     */
    @Test
    public void testSquareRotate() {
        final SquareRotate rotate = new SquareRotate();
        final int[][] actualArray = new int[][] {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        final int[][] expectedArray = new int[][] {{2, 5, 8}, {1, 4, 7}, {0, 3, 6}};
        assertThat(expectedArray, is(rotate.squareRotate(actualArray)));
    }
}