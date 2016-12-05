package ru.avoronin;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *  ��������� �������� �������� ������������.
 *  ��������, ���������, ���������, �������.
 */
public class CalculatorTest {

   /**
    * ������ ����� ��� �����.
    */
    private final double first = 5d;

    /**
    * ������ ����� ��� �����.
    */
    private final double second = 5d;

    /**
     * ���� �������� 2 �����.
     */
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
	double result = first + second;
        calc.add(first, second);
        assertThat(result, is(calc.getResult()));
    }

    /**
     * ���� ��������� 2 �����.
     */
    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
	double result = first - second;
        calc.subtract(first, second);
        assertThat(result, is(calc.getResult()));
    }

    /**
     * ���� ������� 2 �����.
     */
    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
	double result = first / second;
        calc.divide(first, second);
        assertThat(result, is(calc.getResult()));
    }

    /**
     * ���� ��������� 2 �����.
     */
    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
	double result = first * second;
        calc.multiply(first, second);
        assertThat(result, is(calc.getResult()));
    }

    /**
     * ���� �������� ���� result.
     */
    @Test
    public void testGetResult() {
        Calculator calc = new Calculator();
        assertThat(0.0, is(calc.getResult()));
 }
}