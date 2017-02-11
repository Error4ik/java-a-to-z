package ru.job4j;

import ru.job4j.calculator.ICalculator;
import ru.job4j.exception.DivideByZeroException;
import ru.job4j.exception.InvalidOperationException;
import ru.job4j.input.Input;
import ru.job4j.view.IView;

/**
 * Менеджер калькулятора.
 */
public class Manager {

    /**
     * Current calculator.
     */
    private final ICalculator calculator;

    /**
     * Current input.
     */
    private final Input input;

    /**
     * Current view.
     */
    private final IView view;

    /**
     * Constructor.
     *
     * @param calculator calculator.
     * @param input      input.
     * @param view       view.
     */
    public Manager(final ICalculator calculator, final Input input, final IView view) {
        this.calculator = calculator;
        this.input = input;
        this.view = view;
    }

    /**
     * Run calculator operation.
     */
    public void runCalculator() {
        view.showMenu();
        CheckInput checkInput = new CheckInput();

        try {
            while (checkInput.checkInputValue(input.getInput())) {
                checkInput.setResult(
                        calculator.runAction(checkInput.getTypeOperation(),
                        checkInput.getFirstNumber(),
                        checkInput.getSecondNumber())
                );

                view.showResult(checkInput.getResult());
            }
        } catch (NumberFormatException e) {
            System.out.printf("Error! %s\n", "Number format error.");
            //e.printStackTrace();
        } catch (InvalidOperationException | DivideByZeroException e) {
            System.out.printf("Error! %s\n", e.getMessage());
            //e.printStackTrace();
        }
    }
}
