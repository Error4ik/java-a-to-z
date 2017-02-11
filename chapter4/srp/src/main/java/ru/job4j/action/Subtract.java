package ru.job4j.action;

/**
 * Calculator action subtract.
 */
public class Subtract implements Action {

    /**
     * Method subtracts the second number from the first.
     *
     * @param values numbers for the actions
     * @return the result of subtracting two numbers.
     */
    @Override
    public double execute(final double... values) {
        return values[0] - values[1];
    }
}
