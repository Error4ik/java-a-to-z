package ru.job4j.chess.model.figures;


import ru.job4j.chess.model.Point;
import ru.job4j.chess.exception.IllegalMoveException;

/**
 * Король.
 */
public class King extends Figure {

    /**
     * Конструктор принимает на вход.
     *
     * @param point   ячейка в которой находится данная фигура.
     * @param isWhite цвет, true белый, а если false то черный.
     * @param type    Тип фигуры.
     */
    public King(Point point, boolean isWhite, String type) {
        super(point, isWhite, type);
    }

    /**
     * Метод рассчитывает ход и возвращает путь.
     *
     * @param end Ячейку в которую нужно сделать ход.
     * @return возвращает массив ячеек которые нужно пройти.
     * @throws IllegalMoveException Бросает если ход не валидный.
     */
    @Override
    public Point[] way(Point end) throws IllegalMoveException {
        Point[] points = new Point[1];
        if (this.getPoint().getX() == end.getX() && Math.abs(this.getPoint().getY() - end.getY()) == 1) {
            points[0] = new Point(end.getX(), end.getY());
        } else if (this.getPoint().getY() == end.getY() && Math.abs(this.getPoint().getX() - end.getX()) == 1) {
            points[0] = new Point(end.getX(), end.getY());
        } else if (this.getPoint().getX() != end.getX() && this.getPoint().getY() != end.getY()) {
            if (Math.abs(this.getPoint().getX() - end.getX()) == 1 && Math.abs(this.getPoint().getY() - end.getY()) == 1) {
                points[0] = new Point(end.getX(), end.getY());
            } else {
                throw new IllegalMoveException("Invalid move figure!");
            }
        } else {
            throw new IllegalMoveException("You to not move!");
        }
        return points;
    }

    /**
     * Возвращает новую фигуру с теми же характиристиками но другой координатай.
     * @param point новые координаты.
     * @return фигура.
     */
    @Override
    public Figure clone(Point point) {
        return new King(point, this.isWhite(), this.getType());
    }
}
