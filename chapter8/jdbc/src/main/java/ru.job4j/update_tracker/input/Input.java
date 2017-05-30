package ru.job4j.update_tracker.input;

/**
 *
 */
public interface Input {

    /**
     * метод запрашивает у пользователя ввод и возвращает его.
     * @param msg сообщение для пользователя.
     * @return возвращает ввод пользователя.
     */
    String getInput(String msg);
}
