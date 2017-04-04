package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author Alexey Voronin.
 * @since 04.04.2017.
 */
public class UserTest {

    /**
     * Method getName.
     */
    @Test
    public void whenGetNameThenReturnValidValue() {
        final User user = new User("Alex");
        final String expectedValue = "Alex";

        assertThat(user.getName(), is(expectedValue));
    }

}