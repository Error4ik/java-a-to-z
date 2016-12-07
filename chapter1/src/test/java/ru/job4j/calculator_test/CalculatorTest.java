package ru.job4j.calculator_test;


import ru.job4j.calculator.Calculator;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *  Тестирует основные операции калькулятора.
 *  Сложение, Вычитание, Умножение, Деление.
 */
public class CalculatorTest {

    /**
     * Первое число для теста.
     */
    private final double first = 5d;

    /**
     * Второе число для теста.
     */
    private final double second = 5d;

    /**
     * Тест сложения 2 чисел.
     */
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        final double resultAdd = 10d;
        calc.add(first, second);
        assertThat(calc.getResult(), is(resultAdd));
    }

    /**
     * Тест вычитания 2 чисел.
     */
    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        final double resultSubtract = 0d;
        calc.subtract(first, second);
        assertThat(calc.getResult(), is(resultSubtract));
    }

    /**
     * Тест деления 2 чисел.
     */
    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
        final double resultDivide = 1d;
        calc.divide(first, second);
        assertThat(calc.getResult(), is(resultDivide));
    }

    /**
     * Тест умножения 2 чисел.
     */
    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        final double resultMultiply = 25.0;
        calc.multiply(first, second);
        assertThat(calc.getResult(), is(resultMultiply));
    }

    /**
     * Тест возврата поля result.
     */
    @Test
    public void testGetResult() {
        Calculator calc = new Calculator();
        assertThat(calc.getResult(), is(0d));
    }
}