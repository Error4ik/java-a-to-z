package ru.job4j.lesson1;

import org.junit.Test;
import static org.hamcrest.core.Is.is;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.assertThat;

/**
 * Тест класса CheckByteStream.
 */
public class CheckByteStreamTest {

    /**
     * Возвращает true если число четное.
     */
    @Test
    public void whenEvenNumberThatReturnTrue() {
        final CheckByteStream cb = new CheckByteStream();
        final String string = "40";
        final InputStream input = new ByteArrayInputStream(string.getBytes());
        final boolean expected = true;
        assertThat(cb.isNumber(input), is(expected));
    }

    /**
     * Возвращает false если число не четное.
     */
    @Test
    public void whenOddNumberThenReturnFalse() {
        final CheckByteStream cb = new CheckByteStream();
        final String string = "39";
        final InputStream input = new ByteArrayInputStream(string.getBytes());
        final boolean expected = false;
        assertThat(cb.isNumber(input), is(expected));
    }
}