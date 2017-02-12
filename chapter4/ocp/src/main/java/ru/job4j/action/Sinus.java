package ru.job4j.action;

/**
 * Sinus action.
 */
public class Sinus implements Action {

    /**
     * Method sine of a number.
     *
     * @param value numbers.
     * @return the sum of two numbers.
     */
    @Override
    public double execute(double... value) {
        return Math.sin(value[1]);
    }
}
