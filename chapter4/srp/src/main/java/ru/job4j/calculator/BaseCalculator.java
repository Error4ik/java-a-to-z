package ru.job4j.calculator;

import ru.job4j.action.Add;
import ru.job4j.action.Subtract;
import ru.job4j.action.Multiply;
import ru.job4j.action.Divide;
import ru.job4j.action.Action;
import ru.job4j.exception.InvalidOperationException;

import java.util.HashMap;
import java.util.Map;

/**
 * Base Calculator.
 */
public class BaseCalculator implements ICalculator {

    /**
     * Contains all of the calculator operation.
     */
    private Map<String, Action> map;

    /**
     * Constructor create and fill map.
     */
    public BaseCalculator() {
        this.map = new HashMap<>();
        this.baseFunction();
    }

    /**
     * Method run action.
     *
     * @param key    key for search action.
     * @param values value for calculation.
     * @return action to key.
     * @throws InvalidOperationException invalid operation type.
     */
    @Override
    public double runAction(final String key, final double... values) throws InvalidOperationException {
        if (!this.map.containsKey(key)) {
            throw new InvalidOperationException("Invalid operation");
        }
        return this.map.get(key).execute(values);
    }

    /**
     * Add action to map.
     *
     * @param key    operation key.
     * @param action action.
     */
    protected void addAction(final String key, final Action action) {
        this.map.put(key, action);
    }

    /**
     * Add to map basic function calculator.
     */
    private void baseFunction() {
        addAction("+", new Add());
        addAction("-", new Subtract());
        addAction("*", new Multiply());
        addAction("/", new Divide());
    }
}
