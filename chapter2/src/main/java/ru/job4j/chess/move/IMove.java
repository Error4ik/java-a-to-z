package ru.job4j.chess.move;

import ru.job4j.chess.model.Point;
import ru.job4j.chess.exception.IllegalMoveException;

/**
 * Интерфейс для реализации хода фигуры.
 */
public interface IMove {

    /**
     * Метод для реализации хода фигуры, принимает на вход.
     * @param start координату фигуры которой нужно сделать ход.
     * @param end координату куда нужно сделать ход.
     * @return возвращает массив ячеек которые должна пройти фигура что бы сделать ход.
     * @throws IllegalMoveException кидает если ход не валидный.
     */
    Point[] move(Point start, Point end) throws IllegalMoveException;
}
