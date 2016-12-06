package ru.job4j.factorial_test;

import org.junit.Test;
import ru.job4j.factorial.Factorial;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса Factorial.
 */
public class FactorialTest {

    /**
     * Тест для метода возвращающего факториал числа.
     */
    @Test
    public void testFactorial() {
        Factorial factorial = new Factorial();
        final int number = 5;
        final int factorialFive = 120;
        assertThat(factorialFive, is(factorial.findFactorial(number)));
    }
}