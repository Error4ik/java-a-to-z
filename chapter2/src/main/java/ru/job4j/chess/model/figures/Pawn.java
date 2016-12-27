package ru.job4j.chess.model.figures;


import ru.job4j.chess.model.Point;
import ru.job4j.chess.exception.IllegalMoveException;

/**
 * Пешка.
 */
public class Pawn extends Figure {

    /**
     * Конструктор принимает на вход.
     * @param point ячейка в которой находится данная фигура.
     * @param isWhite цвет, true белый, а если false то черный.
     * @param type Тип фигуры.
     */
    public Pawn(Point point, boolean isWhite, String type) {
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
        return this.isWhite() ? whiteWay(end) : blackWay(end);
    }

    /**
     * Ход белой пешки.
     * @param end конечная точка в которой должна оказаться фигура.
     * @return возвращает массив клеток, которые должна проти фигура что бы оказаться в конечной точке.
     * @throws IllegalMoveException Бросает если ход не валидный.
     */
    private Point[] whiteWay(Point end) throws IllegalMoveException {
        final int startPositionX = 6;
        Point[] points = null;
        if (this.getPoint().getX() == startPositionX && (this.getPoint().getX() - end.getX()) == 2 && this.getPoint().getY() == end.getY()
                || (this.getPoint().getX() - end.getX()) == 1 && this.getPoint().getY() == end.getY()
                || (this.getPoint().getX() - end.getX()) == 1 && Math.abs(this.getPoint().getY() - end.getY()) == 1) {
            points = new Point[this.getPoint().getX() - end.getX()];
            for (int i = 1; i <= points.length; i++) {
                if (this.getPoint().getY() == end.getY()) {
                    points[i - 1] = new Point(this.getPoint().getX() - i, this.getPoint().getY());
                } else if (this.getPoint().getY() > end.getY()) {
                    points[i - 1] = new Point(this.getPoint().getX() - i, this.getPoint().getY() - i);
                } else {
                    points[i - 1] = new Point(this.getPoint().getX() - i, this.getPoint().getY() + i);
                }
            }
        } else {
            throw new IllegalMoveException("Данная фигура не может так ходить.");
        }
        return points;
    }

    /**
     * Ход черной пешки.
     * @param end конечная точка в которой должна оказаться фигура.
     * @return возвращает массив клеток, которые должна проти фигура что бы оказаться в конечной точке.
     * @throws IllegalMoveException Бросает если ход не валидный.
     */
    private Point[] blackWay(Point end) throws IllegalMoveException {
        Point[] points = null;
        final int startPositionX = 1;
        if (this.getPoint().getX() == startPositionX && Math.abs(this.getPoint().getX() - end.getX()) == 2 && this.getPoint().getY() == end.getY()
                || (end.getX() - this.getPoint().getX()) == 1 && this.getPoint().getY() == end.getY()
                || (end.getX() - this.getPoint().getX()) == 1 && Math.abs(this.getPoint().getY() - end.getY()) == 1
                ) {
            points = new Point[Math.abs(this.getPoint().getX() - end.getX())];
            for (int i = 1; i <= points.length; i++) {
                if (this.getPoint().getY() == end.getY()) {
                    points[i - 1] = new Point(this.getPoint().getX() + i, this.getPoint().getY());
                } else if (this.getPoint().getY() > end.getY()) {
                    points[i - 1] = new Point(this.getPoint().getX() + i, this.getPoint().getX() - i);
                } else {
                    points[i - 1] = new Point(this.getPoint().getX() + i, this.getPoint().getY() + i);
                }
            }
        } else {
            throw new IllegalMoveException("Данная фигура не может так ходить.");
        }
        return points;
    }
}
