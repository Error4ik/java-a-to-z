package ru.job4j.max_test;

import ru.job4j.max.Max;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса Max.
 */
public class MaxTest {

    /**
     * Тест возвращает первое число, если оно больше второго.
     */
    @Test
    public void whenTheFirstMoreThanTheSecondTheFirstReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 3;
        assertThat(max.findMaxTwoNumber(first, second), is(first));
    }

    /**
     * Тест возвращает второе число, если оно больше первого.
     */
    @Test
    public void whenTheSecondMoreThanTheFirstTheSecondReturn() {
        Max max = new Max();
        final int first = 7;
        final int second = 9;
        assertThat(max.findMaxTwoNumber(first, second), is(second));
    }

    /**
     * Тест возвращает первое число, если оба числа равны.
     */
    @Test
    public void whenTheFirstEqualsThanTheSecondTheFirstReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 5;
        assertThat(max.findMaxTwoNumber(first, second), is(first));
    }


    /**
     * Тест возвращает первое число, если оно больше второго и третьего.
     */
    @Test
    public void whenTheFirstMoreThanTheSecondAndThirdTheFirstReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 3;
        final int third = 4;
        assertThat(max.findLargeNumberOfThree(first, second, third), is(first));
    }

    /**
     * Тест возвращает второе число, если оно больше первого и третьего.
     */
    @Test
    public void whenTheSecondMoreThanTheFirstAndThirdTheSecondReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 7;
        final int third = 4;
        assertThat(max.findLargeNumberOfThree(first, second, third), is(second));
    }

    /**
     * Тест возвращает второе число, если оно больше первого и третьего.
     */
    @Test
    public void whenTheThirdMoreThanTheFirstAndSecondTheThirdReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 7;
        final int third = 11;
        assertThat(max.findLargeNumberOfThree(first, second, third), is(third));
    }

    /**
     * Тест возвращает второе число, если оно больше первого и третьего.
     */
    @Test
    public void whenTheFirstAndSecondAndThirdEqualsThenFirstReturn() {
        Max max = new Max();
        final int first = 5;
        final int second = 5;
        final int third = 5;
        assertThat(max.findLargeNumberOfThree(first, second, third), is(first));
    }
}