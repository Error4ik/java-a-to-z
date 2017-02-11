package ru.job4j;

import ru.job4j.calculator.BaseCalculator;
import ru.job4j.input.ConsoleInput;
import ru.job4j.view.ConsoleView;

/**
 * Start calculator class.
 */
public class StartCalculator {

    /**
     * Main method, init and run manager class.
     * @param args arguments.
     */
    public static void main(String[] args) {
        Manager manager = new Manager(new BaseCalculator(), new ConsoleInput(), new ConsoleView());
        manager.runCalculator();
    }
}
