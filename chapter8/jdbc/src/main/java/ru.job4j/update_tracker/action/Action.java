package ru.job4j.update_tracker.action;

import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.tracker.Tracker;

/**
 * Интерфейс для действий.
 */
public interface Action {

    /**
     * Возвращает номер под которым данное действие находится в меню.
     * @return номер.
     */
    String getId();

    /**
     * Метод для отображения информации о действие.
     * @return строку с номером и именем действия.
     */
    String showItem();

    /**
     * Метод который выполняет всю основную работу пункта меню.
     * @param tracker трекер.
     * @param inputData выбор пользователя.
     */
    void execute(Tracker tracker, Input inputData);
}
