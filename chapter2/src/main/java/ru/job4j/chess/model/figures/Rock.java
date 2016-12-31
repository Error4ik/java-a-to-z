package ru.job4j.chess.model.figures;


import ru.job4j.chess.model.Point;
import ru.job4j.chess.exception.IllegalMoveException;
import ru.job4j.chess.move.LineMove;

/**
 * Ладья.
 */
public class Rock extends Figure {

    /**
     * Конструктор принимает на вход.
     * @param point ячейка в которой находится данная фигура.
     * @param isWhite цвет, true белый, а если false то черный.
     * @param type Тип фигуры.
     */
    public Rock(Point point, boolean isWhite, String type) {
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
        return new LineMove().move(this.getPoint(), end);
    }

    /**
     * Возвращает новую фигуру с теми же характиристиками но другой координатай.
     * @param point новые координаты.
     * @return фигура.
     */
    @Override
    public Figure clone(Point point) {
        return new Rock(point, this.isWhite(), this.getType());
    }
}
