package ru.job4j.action;

/**
 * Calculate action add.
 */
public class Add implements Action {

    /**
     * Method adds two numbers.
     *
     * @param value numbers.
     * @return the sum of two numbers.
     */
    @Override
    public double execute(final double... value) {
        return value[0] + value[1];
    }
}
