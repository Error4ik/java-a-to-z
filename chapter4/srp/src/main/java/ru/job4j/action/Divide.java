package ru.job4j.action;

import ru.job4j.exception.DivideByZeroException;

/**
 * Calculator action divide.
 */
public class Divide implements Action {

    /**
     * Method divides the first number by the second.
     *
     * @param values
     * @return returns the result of the division.
     * @throws DivideByZeroException Exception divide by zero.
     */
    @Override
    public double execute(final double... values) throws DivideByZeroException {
        if (values[1] == 0) {
            throw new DivideByZeroException("Divide by zero!!");
        }
        return values[0] / values[1];
    }
}
