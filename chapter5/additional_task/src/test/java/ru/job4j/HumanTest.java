package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test Human.
 *
 * @author Alexey Voronin.
 * @since 02.04.2017.
 */
public class HumanTest {

    /**
     * Method getTimeIn.
     */
    @Test
    public void whenGetTimeInThenReturnValidValue() {
        final Human human = new Human("10:30", null);
        final String expectedValue = "10:30";

        assertThat(human.getTimeIn(), is(expectedValue));
    }

    /**
     * Method getTimeOut.
     */
    @Test
    public void whenGetTimeOutThenReturnValidValue() {
        final Human human = new Human(null, "10:30");
        final String expectedValue = "10:30";

        assertThat(human.getTimeOut(), is(expectedValue));
    }

}