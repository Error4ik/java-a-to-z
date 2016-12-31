package ru.job4j.chess.model.figures;

import ru.job4j.chess.model.Point;
import ru.job4j.chess.exception.IllegalMoveException;

import java.util.Objects;

/**
 *
 */
public abstract class Figure {

    /**
     * Ячейка в которой находится фигура на поле.
     */
    private Point point;

    /**
     * Хранит состояние цвета фигуры. Если true то белая.
     */
    private final boolean isWhite;

    /**
     * Хранит тип фигуры.(пешка, слон, ладья...).
     */
    private final String type;

    /**
     * Конструктор инициализирует состояние фигуры.
     * @param point ячейка на поле.
     * @param isWhite белая или черная.
     * @param type тип.
     */
    public Figure(final Point point, final boolean isWhite, final String type) {
        this.point = point;
        this.isWhite = isWhite;
        this.type = type;
    }

    /**
     * Метод в котором реализована логика по которой та или иная фигура может ъодить.
     * @param point клетка в которую нужно сделать ход.
     * @return возвращает массив клеток которые нужно пройти до конечной точки.
     * @throws IllegalMoveException кидает исключение если ход не валидный.
     */
    public abstract Point[] way(Point point) throws IllegalMoveException;

    /**
     * Возвращает ячейку в которой стоит данная фигура.
     * @return ячейка.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Возвращает цвет фигуры.
     * @return true или false.
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Копирует текущую фигуру изменяя её координаты.
     * @param point новые координаты.
     * @return фигуру.
     */
    public abstract Figure clone(final Point point);

    /**
     * Геттер для поля тип.
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * Возвращает описание фигуры.
     * @return ячейка, цвет и тип.
     */
    @Override
    public String toString() {
        return "Figure{"
                + "point = "
                + this.getPoint().getX() + "." + this.getPoint().getY()
                + ", isWhite = "
                + isWhite
                + ", type = '"
                + type
                + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Figure)) {
            return false;
        }
        Figure figure = (Figure) o;
        return isWhite() == figure.isWhite()
                && Objects.equals(getType(), figure.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isWhite(), getType());
    }
}
