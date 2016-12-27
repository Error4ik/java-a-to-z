package ru.job4j.chess.exception;

/**
 * Если попытаться сделать не правильный ход.
 */
public class IllegalMoveException extends Exception {

    /**
     * Конструктор принимает сообщение и передает родителю.
     * @param message сообщение.
     */
    public IllegalMoveException(final String message) {
        super(message);
    }
}
