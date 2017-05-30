package ru.job4j.update_tracker.exception;

/**
 * Кидается при выходе за рамки пунктов меню.
 */
public class MenuOutException extends RuntimeException {

    /**
     * КОнструктор примимает сообщение об ошибке.
     * @param msg сообщение.
     */
    public MenuOutException(final String msg) {
        System.out.print(msg);
    }
}
