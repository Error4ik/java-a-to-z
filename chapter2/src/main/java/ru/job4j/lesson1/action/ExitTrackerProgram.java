package ru.job4j.lesson1.action;

import ru.job4j.lesson1.input.Input;
import ru.job4j.lesson1.tracker.Tracker;

/**
 * Выполняет выход из программы.
 */
public class ExitTrackerProgram extends BaseAction {

    /**
     * конструетор принимает.
     *
     * @param id   номер задачи.
     * @param name название задачи.
     */
    public ExitTrackerProgram(final String id, final String name) {
        super(id, name);
    }

    /**
     * Метод выполняет логику пункта меню (Выход).
     *
     * @param tracker   трекер.
     * @param inputData выбор пользователя.
     */
    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        System.out.print("Exit program");
        //System.exit(0);
    }
}
