package ru.job4j.action;

/**
 * Cosine action.
 */
public class Cosine implements Action {

    /**
     * Method cosine of a number.
     *
     * @param value numbers.
     * @return cosine.
     */
    @Override
    public double execute(double... value) {
        return Math.cos(value[1]);
    }
}
