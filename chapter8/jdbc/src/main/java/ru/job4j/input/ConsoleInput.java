package ru.job4j.input;

import java.util.Scanner;

/**
 * User's console input.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class ConsoleInput implements Input {

    @Override
    public String getInput(final String msg) throws NumberFormatException {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print(msg);
        return scanner.nextLine();
    }
}
