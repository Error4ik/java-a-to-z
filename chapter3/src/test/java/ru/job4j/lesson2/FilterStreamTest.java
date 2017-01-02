package ru.job4j.lesson2;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса FilterStream.
 */
public class FilterStreamTest {

    /**
     * Тест метода dropAbuses.
     * @throws Exception IOException.
     */
    @Test
    public void dropAbuses() throws Exception {
        final FilterStream filterStream = new FilterStream();
        final String[] abuse = {"hello", "hi", "good"};
        final String text = "hello good nice hi god work stream hi people";
        final String expected = "nice god work stream people";
        final String actual;
        try (final InputStream inputStream = new ByteArrayInputStream(text.getBytes());
             final OutputStream outputStream = new ByteArrayOutputStream();) {
            filterStream.dropAbuses(inputStream, outputStream, abuse);
            actual = outputStream.toString();
        }
        assertThat(actual, is(expected));
    }

}