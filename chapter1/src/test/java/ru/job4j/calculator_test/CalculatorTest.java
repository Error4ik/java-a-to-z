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
    private final double first = 5.0;

    /**
     * Второе число для теста.
     */
    private final double second = 5.0;

    /**
     * Тест сложения 2 чисел.
     */
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        final double resultAdd = 10.0;
        calc.add(first, second);
        assertThat(resultAdd, is(calc.getResult()));
    }

    /**
     * Тест вычитания 2 чисел.
     */
    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        final double resultSubtract = 0.0;
        calc.subtract(first, second);
        assertThat(resultSubtract, is(calc.getResult()));
    }

    /**
     * Тест деления 2 чисел.
     */
    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
        final double resultDivide = 1.0;
        calc.divide(first, second);
        assertThat(resultDivide, is(calc.getResult()));
    }

    /**
     * Тест умножения 2 чисел.
     */
    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        final double resultMultiply = 25.0;
        calc.multiply(first, second);
        assertThat(resultMultiply, is(calc.getResult()));
    }

    /**
     * Тест возврата поля result.
     */
    @Test
    public void testGetResult() {
        Calculator calc = new Calculator();
        assertThat(0.0, is(calc.getResult()));
    }
}