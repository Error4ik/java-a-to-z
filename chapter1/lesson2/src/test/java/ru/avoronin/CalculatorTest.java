package avoronin;

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
    private final double first = 5.0;

    /**
    * ������ ����� ��� �����.
    */
    private final double second = 5.0;

    /**
    * ��������� �������� ��� �����.
    */
    private final double resultAdd = 10.0;

    /**
    * ��������� ��������� ��� �����.
    */
    private final double resultSubtract = 0.0;

    /**
    * ��������� ������� ��� �����.
    */
    private final double resultDivide = 1.0;

    /**
    * ��������� ��������� ��� �����.
    */
    private final double resultMultiply = 25.0;

    /**
     * ���� �������� 2 �����.
     */
    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        calc.add(first, second);
        assertThat(resultAdd, is(calc.getResult()));
    }

    /**
     * ���� ��������� 2 �����.
     */
    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        calc.subtract(first, second);
        assertThat(resultSubtract, is(calc.getResult()));
    }

    /**
     * ���� ������� 2 �����.
     */
    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
        calc.divide(first, second);
        assertThat(resultDivide, is(calc.getResult()));
    }

    /**
     * ���� ��������� 2 �����.
     */
    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        calc.multiply(first, second);
        assertThat(resultMultiply, is(calc.getResult()));
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