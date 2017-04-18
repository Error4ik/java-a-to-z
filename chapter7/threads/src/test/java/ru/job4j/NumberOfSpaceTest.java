package ru.job4j;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Number of space test.
 *
 * @author Alexey Voronin.
 * @since 12.04.2017.
 */
public class NumberOfSpaceTest {

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
    public void whenStartNewThreadThenCountsTheNumberOfSpaceInAString() throws InterruptedException {
        final String inputValue = "One Two Three  .";
        final Thread thread = new Thread(new NumberOfSpace(inputValue), "Space");
        final String expectedValue = String.format("Space - start!%s%s is running Number of space: 4%sSpace - finish!%s",
                separator, thread.getName(), separator, separator);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        thread.start();
        thread.join();

        assertThat(outputStream.toString(), is(expectedValue));
    }

}