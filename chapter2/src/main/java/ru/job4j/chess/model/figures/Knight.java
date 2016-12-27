package ru.job4j.chess.model.figures;


import ru.job4j.chess.exception.IllegalMoveException;
import ru.job4j.chess.model.Point;

/**
 * Конь.
 */
public class Knight extends Figure {

    /**
     * Конструктор принимает на вход.
     * @param point ячейка в которой находится данная фигура.
     * @param isWhite цвет, true белый, а если false то черный.
     * @param type Тип фигуры.
     */
    public Knight(Point point, boolean isWhite, String type) {
        super(point, isWhite, type);
    }

    /**
     * Метод рассчитывает ход и возвращает путь.
     * @param end Ячейку в которую нужно сделать ход.
     * @return возвращает массив ячеек которые нужно пройти.
     * @throws IllegalMoveException Бросает если ход не валидный.
     */
    @Override
    public Point[] way(Point end) throws IllegalMoveException {
        Point[] points = new Point[1];
        if (Math.abs(this.getPoint().getX() - end.getX()) == 2 && Math.abs(this.getPoint().getY() - end.getY()) == 1
                || Math.abs(this.getPoint().getX() - end.getX()) == 1 && Math.abs(this.getPoint().getY() - end.getY()) == 2) {
            points[0] = new Point(end.getX(), end.getY());
        } else {
            throw new IllegalMoveException("Эта фигура не может так ходить");
        }
        return points;
    }
}
