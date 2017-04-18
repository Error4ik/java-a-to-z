package ru.job4j;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Number of word test.
 *
 * @author Alexey Voronin.
 * @since 12.04.2017.
 */
public class NumberOfWordTest {

    /**
     * String separator.
     */
    private final String separator = System.getProperty("line.separator");

    /**
     * Method run.
     *
     * @throws InterruptedException exception.
     */
    @Test
    public void whenStartNewThreadThenCountsTheNumberOfWordInAString() throws InterruptedException {
        final String inputValue = "One Two Three  bye.";
        final Thread thread = new Thread(new NumberOfWord(inputValue), "Word");
        final String expectedValue = String.format("Word - start!%s%s is running Number of word: 4%sWord - finish!%s",
                separator, thread.getName(), separator, separator);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        thread.start();
        thread.join();

        assertThat(outputStream.toString(), is(expectedValue));
    }

}