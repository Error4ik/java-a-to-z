package ru.job4j.calculator;

/**
 * class Calculator.
 * @author Alexey Voronin.
 */
public class Calculator {

    /**
     * —одержит результат вычислений.
     */
    private double result;

    /**
     * ћетод складывает 2 числа.
     * @param firstNumber первое число.
     * @param secondNumber второе число.
     * записывает в поле result сумму двух чисел.
     */
    public void add(double firstNumber, double secondNumber) {
        this.result = firstNumber + secondNumber;
    }

    /**
     * ћетод вычитает второе число из первого.
     * @param firstNumber первое число.
     * @param secondNumber второе число.
     * записывает в поле result получившийс€ после вычитани€ результат.
     */
    public void subtract(double firstNumber, double secondNumber) {
        this.result = firstNumber - secondNumber;
    }

    /**
     * ћетод делит первое число на второе.
     * @param firstNumber первое число.
     * @param secondNumber второе число.
     * записывает в поле result получившийс€ после делени€ результат.
     */
    public void divide(double firstNumber, double secondNumber) {
        this.result = firstNumber / secondNumber;
    }

    /**
     * ћетод умножает 2 числа.
     * @param firstNumber первое число.
     * @param secondNumber второе число.
     * записывает в поле result результат умножени€.
     */
    public void multiply(double firstNumber, double secondNumber) {
        this.result = firstNumber * secondNumber;
    }

    /**
     *
     * @return возвращает result.
     */
    public double getResult() {
        return this.result;
    }
}
