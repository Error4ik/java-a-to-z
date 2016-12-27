package ru.job4j.chess.exception;

/**
 * Если попытаться сделать ход с клетки где нет фигуры.
 */
public class NoFigureException extends Exception {

    /**
     * Конструктор принимает на вход сообщение и передает родителю.
     * @param message сообщение.
     */
    public NoFigureException(final String message) {
        super(message);
    }
}
