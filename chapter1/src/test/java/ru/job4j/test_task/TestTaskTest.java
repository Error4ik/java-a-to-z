package ru.job4j.test_task;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест TaskTest.
 */
public class TestTaskTest {

    /**
     * Тест если подстрока не находится в строке.
     */
    @Test
    public void whenTheSubstringIsNotInLineThenReturnFalse() {
        String text = "Hello World";
        String subText = "abs";
        TestTask task = new TestTask();
        final boolean result = task.isContains(text, subText);
        assertThat(result, is(false));
    }

    /**
     * Тест если подстрока находится в конце строки.
     */
    @Test
    public void whenTheSubstringIsTheEndOfTheLineThenReturnTrue() {
        String text = "Hello World";
        String subText = "rld";
        TestTask task = new TestTask();
        final boolean result = task.isContains(text, subText);
        assertThat(result, is(true));
    }

    /**
     * Тест если подстрока находится в начале строки.
     */
    @Test
    public void whenTheSubstringIsTheStartOfTheLineThenReturnTrue() {
        String text = "Hello World";
        String subText = "hell";
        TestTask task = new TestTask();
        final boolean result = task.isContains(text, subText);
        assertThat(result, is(true));
    }

    /**
     * Тест если подстрока находится в середине строки.
     */
    @Test
    public void whenTheSubstringIsInTheMiddleOfTheLineThenReturnTrue() {
        String text = "Hello World";
        String subText = "o wo";
        TestTask task = new TestTask();
        final boolean result = task.isContains(text, subText);
        assertThat(result, is(true));
    }

    /**
     * Тест если подстрока находится в начале строки.
     */
    @Test
    public void whenSubstringLongerLineThenReturnFalse() {
        String text = "Hello World";
        String subText = "hello worlds";
        TestTask task = new TestTask();
        final boolean result = task.isContains(text, subText);
        assertThat(result, is(false));
    }
}