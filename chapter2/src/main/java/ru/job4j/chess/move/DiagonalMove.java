package ru.job4j.chess.move;

import ru.job4j.chess.model.Point;

/**
 * Ход по диагонали.
 */
public class DiagonalMove implements IMove {

    /**
     * Метод который вычисляет ячейки которые должна пройти фигура что бы оказаться в конечной точке по даигонали.
     * @param start координату фигуры которой нужно сделать ход.
     * @param end координату куда нужно сделать ход.
     * @return возвращает массив ячеек.
     */
    @Override
    public Point[] move(Point start, Point end) {
        Point[] points = null;
        if (start.getY() > end.getY()) {
            points = new Point[start.getY() - end.getY()];
            if (start.getX() + start.getY() == end.getX() + end.getY()) {
                for (int i = 1; i <= points.length; i++) {
                    points[i - 1] = new Point(start.getX() + i, start.getY() - i);
                }
            } else if (start.getX() - start.getY() == end.getX() - end.getY()) {
                for (int i = 1; i <= points.length; i++) {
                    points[i - 1] = new Point(start.getX() - i, start.getY() - i);
                }
            }
        } else {
            points = new Point[Math.abs(start.getY() - end.getY())];
            if (start.getX() + start.getY() == end.getX() + end.getY()) {
                for (int i = 1; i <= points.length; i++) {
                    points[i - 1] = new Point(start.getX() - i, start.getY() + i);
                }
            } else if (start.getX() - start.getY() == end.getX() - end.getY()) {
                for (int i = 1; i <= points.length; i++) {
                    points[i - 1] = new Point(start.getX() + i, start.getY() + i);
                }
            }
        }
        return points;
    }
}
