package ru.job4j.action;

/**
 * Involution action.
 */
public class Involution implements Action {

    /**
     * Method degree of number.
     *
     * @param value numbers.
     * @return degree of number.
     */
    @Override
    public double execute(double... value) {
        return Math.pow(value[0], value[1]);
    }
}
