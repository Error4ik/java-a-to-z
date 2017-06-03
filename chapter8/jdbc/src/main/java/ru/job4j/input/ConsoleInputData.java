package ru.job4j.update_tracker.input;

import java.util.Scanner;

/**
 * User's console input.
 */
public class ConsoleInputData implements Input {

    @Override
    public String getInput(final String msg) throws NumberFormatException {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print(msg);
        return scanner.nextLine();
    }
}