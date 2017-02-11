package ru.job4j.calculator;

import ru.job4j.exception.InvalidOperationException;

/**
 * Calculator interface.
 */
public interface ICalculator {

    /**
     * Method run action.
     *
     * @param key    key for search action.
     * @param values value for calculation.
     * @return action to key.
     * @throws InvalidOperationException invalid operation type.
     */
    double runAction(final String key, final double... values) throws InvalidOperationException;
}
