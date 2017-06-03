package ru.job4j.update_tracker.models;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test Task.
 *
 * @author Alexey Voronin.
 * @since 02.06.2017.
 */
public class TaskTest {

    /**
     * GetName().
     */
    @Test
    public void whenGetNameThenReturnValidName() {
        final Task task = new Task(0, "Task", null, 0);
        final String expectedValue = "Task";

        final String actualValue = task.getName();
        assertThat(actualValue, is(expectedValue));
    }

    /**
     * GetDescription().
     */
    @Test
    public void whenGetDescriptionThenReturnValidDescription() {
        final Task task = new Task(0, null, "Desc", 0);
        final String expectedValue = "Desc";

        final String actualValue = task.getDescription();
        assertThat(actualValue, is(expectedValue));
    }

    /**
     * GetId().
     */
    @Test
    public void whenGetIdThenReturnValidId() {
        final Task task = new Task(5, null, null, 0);
        final int expectedValue = 5;

        final int actualValue = task.getId();
        assertThat(actualValue, is(expectedValue));
    }
}