package ru.job4j.lesson1.input;

import java.util.Scanner;

/**
 * Класс реализующий интерфейс Input.
 */
public class ConsoleInputData implements Input {

    /**
     * метод запрашивает у пользователя ввод и возвращает его, выводя запрос для пользователя на консоль.
     * @param msg сообщение для пользователя.
     * @return возвращает ввод пользователя.
     * @throws NumberFormatException кидает если при выборе пункта меню ввести не число, а например строку.
     */
    public String getInput(String msg) throws NumberFormatException {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.print(msg);
        return scanner.nextLine();
    }
}