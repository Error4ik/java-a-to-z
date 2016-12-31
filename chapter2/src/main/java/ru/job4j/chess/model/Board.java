package ru.job4j.chess.model;

import ru.job4j.chess.exception.IllegalMoveException;
import ru.job4j.chess.model.figures.Figure;

/**
 * Поле.
 */
public class Board {

    /**
     * Хранит массив фигур.
     */
    private Figure[][] figures;

    /**
     * Конструктор.
     * @param fieldSize размер игрового поля.
     */
    public Board(final int fieldSize) {
        this.figures = new Figure[fieldSize][fieldSize];
    }

    /**
     * Метод возвращает фигуру в указанной клекте.
     * @param point клетка поля.
     * @return возвращает фигуру, а если фигуры не оказалось null.
     * @throws IllegalMoveException Бросает исключение если поинт не валидный.
     */
    public Figure getFigure(Point point) throws IllegalMoveException {
        if (!(point.getX() >= 0 && point.getX() < this.getSizeBoard() && point.getY() >= 0 && point.getY() < this.getSizeBoard())) {
            throw new IllegalMoveException("Выход за рамки поля!");
        }
        return this.figures[point.getX()][point.getY()] == null ? null : this.figures[point.getX()][point.getY()];
    }

    /**
     * Добавляет фигуру на поле.
     * @param figure фигура которую нужно добавить.
     */
    public void addFigure(final Figure figure) {
        this.figures[figure.getPoint().getX()][figure.getPoint().getY()] = figure;
    }

    /**
     * Удаляет фигуру с поля.
     * @param figure фигура которую нужно удалить.
     */
    public void removeFigure(final Figure figure) {
        if (figure != null) {
            this.figures[figure.getPoint().getX()][figure.getPoint().getY()] = null;
        }
    }

    /**
     * Меняет фигуру в массиве в зависимости от хода.
     * @param oldFigure старая фигура.
     * @param newFigure новая фигура.
     */
    public void replace(final Figure oldFigure, final Figure newFigure) {
        this.addFigure(newFigure);
        this.removeFigure(oldFigure);
    }

    /**
     * Возвращает массив всего поля.
     * @return поле фигур.
     */
    public Figure[][] getFigures() {
        return figures;
    }

    /**
     * Возвращает размер поля.
     * @return размер.
     */
    public int getSizeBoard() {
        return this.figures.length;
    }
}
