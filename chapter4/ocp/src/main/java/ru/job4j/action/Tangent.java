package ru.job4j.action;

/**
 * Tangent action.
 */
public class Tangent implements Action {

    /**
     * Method tangent of a number.
     *
     * @param value numbers.
     * @return tangent.
     */
    @Override
    public double execute(double... value) {
        return Math.tan(value[1]);
    }
}
