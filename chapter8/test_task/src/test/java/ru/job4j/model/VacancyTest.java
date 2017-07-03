package ru.job4j.model;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Vacancy model test.
 *
 * @author Alexey Voronin.
 * @since 03.07.2017.
 */
public class VacancyTest {

    /**
     * Vacancy.
     */
    private final Vacancy vacancy = new Vacancy("Java developer", "Java java java", new Date(117, 0, 0, 0, 0));

    /**
     * Method getTitle.
     */
    @Test
    public void wheGetTitleThenReturnValidTitle() {
        final String expectedValue = "Java developer";
        final String actualValue = vacancy.getTitle();

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Method getBody.
     */
    @Test
    public void whenGetBodyThenReturnValidText() {
        final String expectedValue = "Java java java";
        final String actualValue = vacancy.getBody();

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Method getCreateDate.
     */
    @Test
    public void whenGetCreateDateThenReturnValidDate() {
        final long expectedValue = new Date(117, 0, 0, 0, 0).getTime();
        final long actualValue = vacancy.getCreateDate().getTime();

        assertThat(actualValue, is(expectedValue));
    }

}