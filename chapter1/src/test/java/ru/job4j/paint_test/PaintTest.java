package ru.job4j.paint_test;

import ru.job4j.paint.Paint;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса Paint.
 */
public class PaintTest {

    /**
     * Тест рисование пирамиды.
     * Если в тест передали значение h равным 0.
     */
    @Test
    public void whenHeightEqualsZeroThenReturnEmptyString() {
        final Paint p = new Paint();
        final int height = 0;
        String result = "";
        assertThat(result, is(p.piramide(height)));
    }

    /**
     * Тест рисование пирамиды.
     * Если в тест передали значение h равным 1.
     */
    @Test
    public void whenHeightEqualsOneThenReturnOneSymbolString() {
        final Paint p = new Paint();
        final int height = 1;
        String result = "^ " + System.getProperty("line.separator");
        assertThat(result, is(p.piramide(height)));
    }

    /**
     * Тест рисование пирамиды.
     * Если в тест передали значение h больше 1.
     */
    @Test
    public void whenHeightMoreThatOneThenReturnString() {
        final Paint p = new Paint();
        final int height = 2;
        String result = " ^ " + System.getProperty("line.separator") + "^ ^ " +  System.getProperty("line.separator");
        assertThat(result, is(p.piramide(height)));
    }
}