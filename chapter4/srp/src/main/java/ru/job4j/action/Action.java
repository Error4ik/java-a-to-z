package ru.job4j.action;

/**
 * Calculator action.
 */
public interface Action {

    /**
     * Method arithmetic action.
     *
     * @param value numbers.
     * @return result.
     */
    double execute(final double... value);
}
