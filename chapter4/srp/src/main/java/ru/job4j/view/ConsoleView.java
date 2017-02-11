package ru.job4j.view;

/**
 * Console view calculator.
 */
public class ConsoleView implements IView {

    /**
     * Separator to string.
     */
    private String sep = System.getProperty("line.separator");

    /**
     * Show menu application.
     */
    @Override
    public void showMenu() {
        System.out.printf("%s%s%s%s", "Enter arithmetic operation. Eg: 5 + 5", sep,
                "For arithmetic operations with previous results. Eg: + 10", sep);
    }

    /**
     * Show result operation.
     *
     * @param result result operation.
     */
    @Override
    public void showResult(final double result) {
        if (result % 1 == 0) {
            System.out.printf("%sResult: %s%s", sep, (long) result, sep);
        } else {
            System.out.printf("%sResult: %.2f%s", sep, result, sep);
        }
    }
}
