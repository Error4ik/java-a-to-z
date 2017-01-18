package ru.job4j.lesson5;

import java.util.Scanner;

/**
 * Пользовательский ввод.
 */
public class ConsoleInput implements Input {

    /**
     * Читает ввод с консоли.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Метод читает пользовательский ввод и возвращает его.
     *
     * @return возвращает строку введенную в консоль.
     */
    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
