package ru.job4j.chess.model;

/**
 * Ячейка на игровом поле.
 */
public class Point {

    /**
     * координата Х.
     */
    private final int x;

    /**
     * Координата Y.
     */
    private final int y;

    /**
     * Конструктор для инициализации координат.
     * @param x Х.
     * @param y Y.
     */
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Геттер для координаты Х.
     * @return Х.
     */
    public int getX() {
        return x;
    }

    /**
     * Геттер для координаты Y.
     * @return Y.
     */
    public int getY() {
        return y;
    }

    /**
     * Возвращает описание координат.
     * @return X and Y.
     */
    @Override
    public String toString() {
        return "Point{"
                + "x = "
                + x
                + ", y = "
                + y
                + '}';
    }
}
