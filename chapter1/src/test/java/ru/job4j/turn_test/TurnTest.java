package ru.job4j.turn_test;

import ru.job4j.turn.Turn;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест перевота массива.
 */
public class TurnTest {

    /**
     * Тестирует переворот массива.
     */
    @Test
    public void testBack() {
        final Turn turn = new Turn();
        int[] array = new int[] {0, 1, 2};
        int[] expectedArray = new int[] {2, 1, 0};
        assertThat(expectedArray, is(turn.back(array)));
    }
}