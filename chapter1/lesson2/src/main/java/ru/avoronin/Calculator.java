package avoronin;

/**
 * class Calculator.
 * @author Alexey Voronin.
 */

public class Calculator {

    /**
     * Содержит результат вычислений.
    */
    private double result;

    /**
     * Метод складывает 2 числа.
     * @param firstNumber первое число.
     * @param secondNumber второе число.
     * записывает в поле result сумму двух чисел.
     */
    public void add(double firstNumber, double secondNumber) {
        this.result = firstNumber + secondNumber;
    }

   /**
    * Метод вычитает второе число из первого.
    * @param firstNumber первое число.
    * @param secondNumber второе число.
    * записывает в поле result получившийся после вычитания результат.
    */
    public void subtract(double firstNumber, double secondNumber) {
        this.result = firstNumber - secondNumber;
    }

    /**
     * Метод делит первое число на второе.
     * @param firstNumber первое число.
     * @param secondNumber второе число.
     * записывает в поле result получившийся после деления результат.
     */
    public void divide(double firstNumber, double secondNumber) {
        this.result = firstNumber / secondNumber;
    }

    /**
     * Метод умножает 2 числа.
     * @param firstNumber первое число.
     * @param secondNumber второе число.
     * записывает в поле result результат умножения.
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