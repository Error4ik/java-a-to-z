package ru.job4j.calculator;

/**
 * class Calculator.
 * @author Alexey Voronin.
 */
public class Calculator {

    /**
     * �������� ��������� ����������.
     */
    private double result;

    /**
     * ����� ���������� 2 �����.
     * @param firstNumber ������ �����.
     * @param secondNumber ������ �����.
     * ���������� � ���� result ����� ���� �����.
     */
    public void add(double firstNumber, double secondNumber) {
        this.result = firstNumber + secondNumber;
    }

    /**
     * ����� �������� ������ ����� �� �������.
     * @param firstNumber ������ �����.
     * @param secondNumber ������ �����.
     * ���������� � ���� result ������������ ����� ��������� ���������.
     */
    public void subtract(double firstNumber, double secondNumber) {
        this.result = firstNumber - secondNumber;
    }

    /**
     * ����� ����� ������ ����� �� ������.
     * @param firstNumber ������ �����.
     * @param secondNumber ������ �����.
     * ���������� � ���� result ������������ ����� ������� ���������.
     */
    public void divide(double firstNumber, double secondNumber) {
        this.result = firstNumber / secondNumber;
    }

    /**
     * ����� �������� 2 �����.
     * @param firstNumber ������ �����.
     * @param secondNumber ������ �����.
     * ���������� � ���� result ��������� ���������.
     */
    public void multiply(double firstNumber, double secondNumber) {
        this.result = firstNumber * secondNumber;
    }

    /**
     *
     * @return ���������� result.
     */
    public double getResult() {
        return this.result;
    }
}
