package avoronin;

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
	double result = first + second;
        calc.add(first, second);
        assertThat(result, is(calc.getResult()));
    }

    /**
     * Тест вычитания 2 чисел.
     */
    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
	double result = first - second;
        calc.subtract(first, second);
        assertThat(result, is(calc.getResult()));
    }

    /**
     * Тест деления 2 чисел.
     */
    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
	double result = first / second;
        calc.divide(first, second);
        assertThat(result, is(calc.getResult()));
    }

    /**
     * Тест умножения 2 чисел.
     */
    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
	double result = first * second;
        calc.multiply(first, second);
        assertThat(result, is(calc.getResult()));
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