package ru.job4j;

import ru.job4j.calculator.ExtendedCalculator;
import ru.job4j.input.ConsoleInput;
import ru.job4j.view.ExtendedView;

/**
 * Run calculator class.
 */
public class RunExtendedCalculator {

    /**
     * Main method.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        Manager manager = new Manager(new ExtendedCalculator(), new ConsoleInput(), new ExtendedView());
        manager.runCalculator();
    }
}
