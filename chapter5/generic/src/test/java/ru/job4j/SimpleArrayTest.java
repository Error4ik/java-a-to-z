package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * SimpleArray test.
 *
 * @author Alexey Voronin.
 * @since 12.03.2017.
 */
public class SimpleArrayTest {

    /**
     * Method add.
     */
    @Test
    public void whenItemIsAddedShouldReturnValidValue() {
        final int size = 1;
        final SimpleArray<Integer> simpleArray = new SimpleArray<>(size);
        final Integer inputValue = 3;

        simpleArray.add(inputValue);
        final int actualValue = simpleArray.get(0);

        assertThat(actualValue, is(inputValue));
    }

    /**
     * Method delete.
     */
    @Test()
    public void whenItemIsDeleteShouldReturnNull() {
        final int size = 2;
        final SimpleArray<Integer> simpleArray = new SimpleArray<>(size);
        final Integer inputValue = 5;

        simpleArray.add(inputValue);
        simpleArray.add(inputValue);
        simpleArray.delete(0);

        assertNull(simpleArray.get(0));
    }

    /**
     * Method update.
     */
    @Test
    public void whenValueUpdatedShouldReturnUpdatedValue() {
        final int size = 2;
        final SimpleArray<Integer> simpleArray = new SimpleArray<>(size);
        final Integer inputValue = 5;
        final Integer updateValue = 9;

        simpleArray.add(inputValue);
        simpleArray.add(inputValue);
        simpleArray.update(0, updateValue);

        assertThat(simpleArray.get(0), is(updateValue));
    }
}