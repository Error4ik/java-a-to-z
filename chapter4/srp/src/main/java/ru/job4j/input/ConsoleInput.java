package ru.job4j.input;

import java.util.Scanner;

/**
 * Console user input.
 */
public class ConsoleInput implements Input {

    /**
     * To get input from the console.
     */
    private Scanner scanner;

    /**
     * Constructor.
     */
    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * The method for obtaining user input.
     *
     * @return array input strings.
     */
    @Override
    public String[] getInput() {
        return scanner.nextLine().trim().replaceAll("[\\s]+", " ").split(" ");
    }
}
