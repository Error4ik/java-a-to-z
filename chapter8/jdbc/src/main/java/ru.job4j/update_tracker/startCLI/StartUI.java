package ru.job4j.update_tracker.startCLI;

import ru.job4j.update_tracker.exception.MenuOutException;
import ru.job4j.update_tracker.input.ConsoleInputData;
import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.tracker.MenuTracker;
import ru.job4j.update_tracker.tracker.Tracker;

/**
 * Класс запускает трекер меню.
 */
public class StartUI {

    /**
     * Ввод вывод для пользователя.
     */
    private Input input;

    /**
     * трекер меню для управления трекером.
     */
    private MenuTracker menuTracker;

    /**
     * Конструктор инициализирует переменные.
     *
     * @param input       ввод вывод для пользователя.
     * @param menuTracker меню трекера.
     */
    public StartUI(Input input, MenuTracker menuTracker) {
        this.input = input;
        this.menuTracker = menuTracker;
    }

    /**
     * Запускает работу трекера, выводи на экран меню трекера и запрашивает у пользователя ввод.
     */
    public void start() {
        int value = 0;
        final int exitNumber = 9;
        do {
            divideLineMenu();
            menuTracker.showMenu();
            divideLineMenu();
            try {
                value = Integer.parseInt(input.getInput("Choice Action:\n").trim());
                validEnter(value);
            } catch (NumberFormatException e) {
                System.out.println("This is not a number, try again!");
            } catch (MenuOutException e) {
                System.out.println("Error number is not acceptable, try again");
            }
        } while (value != exitNumber);
    }

    /**
     * Точка входа в программу.
     *
     * @param args массив.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInputData();
        Tracker tracker = new Tracker();
        MenuTracker menuTracker = new MenuTracker(tracker, input);
        menuTracker.fillAction();
        new StartUI(input, menuTracker).start();
    }

    /**
     * Разделитель для лучшей читаемости меню.
     */
    public void divideLineMenu() {
        System.out.println("-------------------------");
    }

    /**
     * Проверяет ввод пользователя. Если пользовател выбрал не существующий пункт меню, бросается исключение.
     *
     * @param number число которое пользователь ввел.
     * @throws MenuOutException бросается если число не соответствует ни одному пункту меню.
     */
    private void validEnter(final int number) throws MenuOutException {
        boolean valid = false;
        for (int i = 0; i < menuTracker.getMenuNumbers().length; i++) {
            if (menuTracker.getMenuNumbers()[i] == number) {
                valid = true;
                break;
            }
        }

        if (valid) {
            menuTracker.select(number);
        } else {
            throw new MenuOutException("");
        }
    }
}
