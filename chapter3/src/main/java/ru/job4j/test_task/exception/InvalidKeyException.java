package ru.job4j.test_task.exception;

/**
 * Исключение. Не парвильно введены ключи для старта программы.
 */
public class InvalidKeyException extends Exception {

    /**
     * Конструктор.
     *
     * @param message сообщение.
     */
    public InvalidKeyException(final String message) {
        super(message);
    }
}
