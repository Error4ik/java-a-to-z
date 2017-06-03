package ru.job4j.update_tracker.input;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * .
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class ConsoleInputDataTest {

    /**
     * getInput.
     */
    @Test
    public void whenGetInputThenReturnMessageAndUserInput() {
        final ConsoleInputData inputData = new ConsoleInputData();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream("Hello".getBytes());
        System.setIn(inputStream);
        final String expectedValue = "Hello";

        String actualValue = inputData.getInput("Say Hello");

        assertThat(actualValue, is(expectedValue));
    }


}