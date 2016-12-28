package ru.job4j.chess;

import ru.job4j.chess.exception.IllegalMoveException;
import ru.job4j.chess.exception.NoFigureException;
import ru.job4j.chess.model.Board;
import ru.job4j.chess.model.Point;
import ru.job4j.chess.model.figures.Rock;
import ru.job4j.chess.model.figures.Knight;
import ru.job4j.chess.model.figures.Bishop;
import ru.job4j.chess.model.figures.Queen;
import ru.job4j.chess.model.figures.King;
import ru.job4j.chess.model.figures.Pawn;
import ru.job4j.chess.model.figures.Figure;

/**
 * Управляет полем, заполняет его и делает ход.
 */
public class ChessGame {

    /**
     * Храни поле.
     */
    private final Board board;

    /**
     * Конструктор для инициализации поля.
     *
     * @param board поле.
     */
    public ChessGame(Board board) {
        this.board = board;
    }

    /**
     * Метод заполняет поле фигурами.
     */
    public void fillBoard() {
        this.board.addFigure(new Rock(new Point(7, 0), true, "Rock"));
        this.board.addFigure(new Knight(new Point(7, 1), true, "Knight"));
        this.board.addFigure(new Bishop(new Point(7, 2), true, "Bishop"));
        this.board.addFigure(new Queen(new Point(7, 3), true, "Queen"));
        this.board.addFigure(new King(new Point(7, 4), true, "King"));
        this.board.addFigure(new Bishop(new Point(7, 5), true, "Bishop"));
        this.board.addFigure(new Knight(new Point(7, 6), true, "Knight"));
        this.board.addFigure(new Rock(new Point(7, 7), true, "Rock"));
        for (int i = 0; i < board.getFigures().length; i++) {
            this.board.addFigure(new Pawn(new Point(6, i), true, "Pawn"));
        }

        this.board.addFigure(new Rock(new Point(0, 0), false, "Rock"));
        this.board.addFigure(new Knight(new Point(0, 1), false, "Knight"));
        this.board.addFigure(new Bishop(new Point(0, 2), false, "Bishop"));
        this.board.addFigure(new Queen(new Point(0, 3), false, "Queen"));
        this.board.addFigure(new King(new Point(0, 4), false, "King"));
        this.board.addFigure(new Bishop(new Point(0, 5), false, "Bishop"));
        this.board.addFigure(new Knight(new Point(0, 6), false, "Knight"));
        this.board.addFigure(new Rock(new Point(0, 7), false, "Rock"));
        for (int i = 0; i < board.getFigures().length; i++) {
            this.board.addFigure(new Pawn(new Point(1, i), false, "Pawn"));
        }
    }

    /**
     * Основной метод для хода, выясняет какая фигура делает ход и вызывает соответствующий метод.
     *
     * @param current Клетка фигуры для которой нужно сделать ход.
     * @param move    Клекта куда нужно сделать ход.
     * @throws IllegalMoveException Кидает если ход не валидный.
     * @throws NoFigureException    Кидает если клетка фигуры которая должна сделать зод пуста.
     */
    public void moveFigure(final Point current, final Point move) throws IllegalMoveException, NoFigureException {
        if (this.getBoard().getFigure(current) == null) {
            throw new NoFigureException("Вы не выбрали фигуру для хода!");
        }
        if (!(move.getX() >= 0 && move.getX() < this.board.getSizeBoard()
                && move.getY() >= 0 && move.getY() < this.board.getSizeBoard())) {
            throw new IllegalMoveException("Выход за рамки поля!");
        }

        final Figure currentFigure = getBoard().getFigure(current);
        final Point[] points = currentFigure.way(move);
        if (currentFigure.getType().equals("Pawn")) {
            pawnMove(points, currentFigure, move);
        } else if (currentFigure.getType().equals("Knight")) {
            knightMove(currentFigure, move);
        } else {
            moveOtherFigure(points, currentFigure, move);
        }
    }

    /**
     * Метод для хода всех фигур кроме пешки и коня.
     *
     * @param points Массив клеток которые дожна пройти фигура что бы оказаться в конечной точке.
     * @param figure фигура которая делает ход.
     * @param move   конечна точка.
     * @throws IllegalMoveException бросает если ход не валидный.
     */
    private void moveOtherFigure(final Point[] points, final Figure figure, final Point move) throws IllegalMoveException {
        for (int i = 0; i < points.length; i++) {
            if (this.getBoard().getFigures()[points[i].getX()][points[i].getY()] != null && i != points.length - 1) {
                throw new IllegalMoveException("Путь фигуры не свободен!");
            }
        }
        if (this.getBoard().getFigure(move) != null && figure.isWhite() == this.getBoard().getFigure(move).isWhite()) {
            throw new IllegalMoveException("Вы не можете ставить фигуру на место где стоит ваша фигура!");
        }

        this.board.removeFigure(figure);
        figure.setPoint(move);
        this.board.addFigure(figure);
    }

    /**
     * Метод хода для пешки.
     *
     * @param points массив ячеек которые нужно пройти что бы оказаться в конечной точке.
     * @param figure фигура которой делается ход.
     * @param move   конечная точка.
     * @throws IllegalMoveException бросает если ход не валидный.
     */
    private void pawnMove(final Point[] points, final Figure figure, final Point move) throws IllegalMoveException {
        if (figure.getPoint().getY() == move.getY()) {
            for (Point point : points) {
                if (this.board.getFigure(point) != null) {
                    throw new IllegalMoveException("Путь фигуры не свободен!");
                }
            }

            this.board.removeFigure(figure);
            figure.setPoint(move);
            this.board.addFigure(figure);
        } else {
            if (this.board.getFigure(move) == null) {
                throw new IllegalMoveException("Данная фигура не может так ходить!");
            } else if (this.board.getFigure(move) != null && figure.isWhite() == this.board.getFigure(move).isWhite()) {
                throw new IllegalMoveException("Вы не можете ставить фигуру на место где стоит ваша фигура!");
            }

            this.board.removeFigure(figure);
            figure.setPoint(move);
            this.board.addFigure(figure);
        }
    }

    /**
     * Метод хода коня.
     *
     * @param figure Фигура которой нужно сделать ход.
     * @param move   конечная точка.
     * @throws IllegalMoveException бросает если ход не валидный.
     */
    private void knightMove(final Figure figure, final Point move) throws IllegalMoveException {
        if (this.getBoard().getFigure(move) != null && board.getFigure(move).isWhite() == figure.isWhite()) {
            throw new IllegalMoveException("Нельзя ставить фигуру на место где стоит ваша фигура!");
        }

        board.removeFigure(figure);
        figure.setPoint(move);
        board.addFigure(figure);
    }

    /**
     * Геттер для поля.
     *
     * @return поле.
     */
    public Board getBoard() {
        return this.board;
    }
}

