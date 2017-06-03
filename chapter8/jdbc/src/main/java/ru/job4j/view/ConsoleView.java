package ru.job4j.update_tracker.view;

/**
 * Print to console.
 *
 * @author Alexey Voronin.
 * @since 02.06.2017.
 */
public class ConsoleView implements IView {

    @Override
    public void execute(final String string) {
        System.out.println("===========================================================");
        System.out.print(string);
        System.out.println("===========================================================");
    }
}
