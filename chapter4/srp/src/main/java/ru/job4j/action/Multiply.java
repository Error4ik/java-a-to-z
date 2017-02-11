package ru.job4j.action;

/**
 * Calculator action multiply.
 */
public class Multiply implements Action {

    /**
     * Method multiplies two numbers.
     *
     * @param values numbers for the actions.
     * @return the multiplication result.
     */
    @Override
    public double execute(final double... values) {
        return values[0] * values[1];
    }
}
