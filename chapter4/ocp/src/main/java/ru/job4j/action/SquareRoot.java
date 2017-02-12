package ru.job4j.action;

/**
 * Square root action.
 */
public class SquareRoot implements Action {

    /**
     * Method the square root of number.
     *
     * @param value numbers.
     * @return square root.
     */
    @Override
    public double execute(double... value) {
        return Math.sqrt(value[1]);
    }
}
