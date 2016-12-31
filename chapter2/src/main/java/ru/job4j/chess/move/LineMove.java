package ru.job4j.chess.move;

import ru.job4j.chess.model.Point;
import ru.job4j.chess.exception.IllegalMoveException;

/**
 * Линейный ход по вертикали или горизонтали.
 */
public class LineMove implements IMove {

    /**
     * Метод который вычисляет ячейки которые должна пройти фигура что бы оказаться в конечной точке по вертикали или горизонтали.
     * @param start координату фигуры которой нужно сделать ход.
     * @param end координату куда нужно сделать ход.
     * @return возвращает массив ячеек.
     * @throws IllegalMoveException кидает если ход не валидный.
     */
    @Override
    public Point[] move(Point start, Point end) throws IllegalMoveException {
        Point[] points = null;
        if (start.getX() == end.getX()) {
            if (start.getY() > end.getY()) {
                points = new Point[Math.abs(start.getY() - end.getY())];
                for (int i = 1; i <= points.length; i++) {
                    points[i - 1] = new Point(start.getX(), start.getY() - i);
                }
            } else {
                points = new Point[Math.abs(start.getY() - end.getY())];
                for (int i = 1; i <= points.length; i++) {
                    points[i - 1] = new Point(start.getX(), start.getY() + i);
                }
            }
        } else if (start.getY() == end.getY()) {
            if (start.getX() > end.getX()) {
                points = new Point[Math.abs(start.getX() - end.getX())];
                for (int i = 1; i <= points.length; i++) {
                    points[i - 1] = new Point(start.getX() - i, start.getY());
                }
            } else {
                points = new Point[Math.abs(start.getX() - end.getX())];
                for (int i = 1; i <= points.length; i++) {
                    points[i - 1] = new Point(start.getX() + i, start.getY());
                }
            }
        } else {
            throw new IllegalMoveException("Invalid move figure!");
        }

        return points;
    }
}
