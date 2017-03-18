package ru.job4j;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * User test.
 *
 * @author Alexey Voronin.
 * @since 18.03.2017.
 */
public class UserTest {

    /**
     * Method getName.
     */
    @Test
    public void whenGetNameThenReturnValidName() {
        final User user = new User("Garry", 0, null);
        final String expectedValue = "Garry";

        assertThat(user.getName(), is(expectedValue));
    }

    /**
     * Method getChildren.
     */
    @Test
    public void whenGetChildrenThenReturnValidChildren() {
        final User user = new User(null, 5, null);
        final int expectedValue = 5;

        assertThat(user.getChildren(), is(expectedValue));
    }

    /**
     * Method getBirthday.
     */
    @Test
    public void whenGetBirthdayThenReturnValidBirthday() {
        final Calendar calendar = new GregorianCalendar(2010, 10, 10);
        final User user = new User(null, 0, calendar);

        assertThat(user.getBirthday(), is(calendar));
    }
}