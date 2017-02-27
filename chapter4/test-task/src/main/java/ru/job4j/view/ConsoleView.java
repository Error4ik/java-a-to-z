package ru.job4j.view;

import ru.job4j.exception.InvalidPointException;
import ru.job4j.model.Field;
import ru.job4j.model.Figure;
import ru.job4j.model.Point;

/**
 * Class displays on the console gaming field.
 *
 * @author Alexey Voronin.
 * @since 25.02.2017.
 */
public class ConsoleView implements IView {

    /**
     * Print field to console.
     *
     * @param field field.
     */
    @Override
    public void showField(final Field field) {
        for (int i = 0; i < field.getFieldSize(); i++) {
            if (i != 0) {
                printSep(field.getFieldSize());
            }
            for (int j = 0; j < field.getFieldSize(); j++) {
                if (j != 0) {
                    System.out.print("|");
                }
                System.out.print(" ");

                try {
                    Figure figure = field.getFigure(new Point(i, j));
                    System.out.print(figure != null ? figure : " ");
                    System.out.print(" ");
                } catch (InvalidPointException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

    /**
     * Show any message.
     * @param message message.
     */
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Separator to cells.
     *
     * @param size number of cells.
     */
    private void printSep(final int size) {
        for (int i = 0; i < size; i++) {
            System.out.print("~~~~");
        }
        System.out.println();
    }
}
