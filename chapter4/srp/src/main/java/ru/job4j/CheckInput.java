package ru.job4j;

/**
 * Class checks user input.
 */
public class CheckInput {

    /**
     * The first digit for the operation.
     */
    private double firstNumber;

    /**
     * The second digit for the operation.
     */
    private double secondNumber;

    /**
     * Type operation for the calculator.
     */
    private String typeOperation;

    /**
     * Result calculator operation.
     */
    private double result;

    /**
     * Check user input method.
     *
     * @param values user data input.
     * @return if data valid return true.
     * @throws NumberFormatException invalid format.
     */
    public boolean checkInputValue(final String[] values) throws NumberFormatException {
        boolean flag = true;
        if ("exit".equalsIgnoreCase(values[0])) {
            flag = false;
        }
        if (flag && values.length == 3) {
            firstNumber = Double.parseDouble(values[0]);
            typeOperation = values[1];
            secondNumber = Double.parseDouble(values[2]);
        } else if (flag && values.length == 2) {
            firstNumber = result;
            typeOperation = values[0];
            secondNumber = Double.parseDouble(values[1]);
        }
        return flag;
    }

    /**
     * Getter.
     *
     * @return result.
     */
    public double getResult() {
        return this.result;
    }

    /**
     * Setter.
     *
     * @param result The value to be set.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * Getter.
     *
     * @return first number.
     */
    public double getFirstNumber() {
        return firstNumber;
    }

    /**
     * Getter.
     *
     * @return second number.
     */
    public double getSecondNumber() {
        return secondNumber;
    }

    /**
     * Getter.
     *
     * @return type operation.
     */
    public String getTypeOperation() {
        return typeOperation;
    }
}
