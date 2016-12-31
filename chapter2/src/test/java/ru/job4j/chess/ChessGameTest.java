package ru.job4j.chess;

import org.junit.Test;
import ru.job4j.chess.exception.IllegalMoveException;
import ru.job4j.chess.exception.NoFigureException;
import ru.job4j.chess.model.Board;
import ru.job4j.chess.model.Point;
import ru.job4j.chess.model.figures.Pawn;
import ru.job4j.chess.model.figures.Rock;
import ru.job4j.chess.model.figures.Queen;
import ru.job4j.chess.model.figures.Knight;
import ru.job4j.chess.model.figures.Bishop;
import ru.job4j.chess.model.figures.Figure;
import ru.job4j.chess.model.figures.King;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 *
 */
public class ChessGameTest {

    /**
     * Размер поля.
     */
    private final int boardSize = 8;

    /**
     * Ход из правого верхнего в левый нижний.
     * @throws Exception Если ход не валидный.
     */
    @Test
    public void moveStartPointRightTopToEndPointLeftDown() throws Exception {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Bishop(new Point(0, 7), true, "Bishop");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(figure.getPoint(), new Point(3, 4));
        assertThat(chessGame.getBoard().getFigure(new Point(3, 4)), is(figure));
    }

    /**
     * Ход из левого нижнего угла в правый верхний.
     * @throws Exception Если ход не валидный.
     */
    @Test
    public void moveStartPointLeftDownToEndPointRightTop() throws Exception {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Bishop(new Point(7, 0), true, "Bishop");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(figure.getPoint(), new Point(6, 1));
        assertThat(chessGame.getBoard().getFigure(new Point(6, 1)), is(figure));
    }

    /**
     * Ход из правого нижнего угла в левый верхний.
     * @throws Exception Если ход не валидный.
     */
    @Test
    public void moveStartPointRightDownToEndPointLeftTop() throws Exception {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Bishop(new Point(7, 7), true, "Bishop");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(figure.getPoint(), new Point(0, 0));
        assertThat(chessGame.getBoard().getFigure(new Point(0, 0)), is(figure));
    }

    /**
     * Ход из левого верхнего угла в правый нижний.
     * @throws Exception Если ход не валидный.
     */
    @Test
    public void moveStartPointLestTopToEndPointRightDown() throws Exception {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Bishop(new Point(0, 0), true, "Bishop");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(figure.getPoint(), new Point(7, 7));
        assertThat(chessGame.getBoard().getFigure(new Point(7, 7)), is(figure));
    }

    /**
     * Если попытаться пойти с пустой клетки.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = NoFigureException.class)
    public void moveToPointEmptyFigure() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.moveFigure(new Point(0, 0), new Point(5, 5));
    }

    /**
     * Юросает исключение если выйти за пределы поля.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void moveToGoBeyondTheBoards() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.fillBoard();
        chessGame.moveFigure(new Point(1, 0), new Point(2, -1));
    }

    /**
     * Бросает исключение если путь фигуры не свободен.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void whenPathFigureNotEmptyThenDropException() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.fillBoard();
        chessGame.moveFigure(new Point(0, 0), new Point(5, 0));
    }

    /**
     * Бросает исключение если на месте куда происходит ход стоит фигура того же цвета.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void whenEndPointFigureEqualsColorThenDropException() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.fillBoard();
        chessGame.moveFigure(new Point(1, 0), new Point(3, 0));
        chessGame.moveFigure(new Point(0, 0), new Point(3, 0));
    }

    /**
     * Бросает исключение если попытаться поставить пешку в то место где стоит фигура того же цвета.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void whenPawnColorEqualsColorFigureEndPointThenDropException() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.getBoard().addFigure(new Pawn(new Point(1, 1), false, "Pawn"));
        chessGame.getBoard().addFigure(new Pawn(new Point(2, 2), false, "Pawn"));
        chessGame.moveFigure(new Point(1, 1), new Point(2, 2));
    }

    /**
     * Тест валидного хода коня по вертикали на 2 клетки.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void moveKnightVerticalTwoPoints() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Knight(new Point(3, 4), false, "Knight");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 4), new Point(5, 3));
        assertThat(chessGame.getBoard().getFigure(new Point(5, 3)), is(figure));
    }

    /**
     * Бросает исключение если конечная точка для хода коня занята фигурой того же цвета.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void whenColorStartPointFigureEqualsColorEndPointFigureThenDropException() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Knight(new Point(3, 4), false, "Knight");
        chessGame.getBoard().addFigure(figure);
        chessGame.getBoard().addFigure(new Pawn(new Point(5, 3), false, "Pawn"));
        chessGame.moveFigure(new Point(3, 4), new Point(5, 3));
    }

    /**
     * Тест хода коня по горизонтали на 2 клетки.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void moveKnightHorizontalTwoPoints() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Knight(new Point(3, 4), false, "Knight");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 4), new Point(2, 2));
        assertThat(chessGame.getBoard().getFigure(new Point(2, 2)), is(figure));
    }

    /**
     * Бросает исключение если ход коня не валидный.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void moveKnightInvalidPoint() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Knight(new Point(3, 4), false, "Knight");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 4), new Point(6, 7));
    }

    /**
     * Бросает исключение если ход черной пешки не валидный.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void whenRightMoveBlackPawnInvalidThenDropException() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.getBoard().addFigure(new Pawn(new Point(1, 1), false, "Pawn"));
        chessGame.moveFigure(new Point(1, 1), new Point(3, 3));
    }

    /**
     * Бросает исключение если ход белой пешки пытается сделать на клетку где стоит фигура тогоже цвета.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void whenColorStartPointPawnEqualsColorEndPointFigureThenDropException() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.getBoard().addFigure(new Pawn(new Point(6, 5), true, "Pawn"));
        chessGame.getBoard().addFigure(new Pawn(new Point(5, 5), true, "Pawn"));
        chessGame.moveFigure(new Point(6, 5), new Point(5, 5));
    }

    /**
     * Атака белой пешки с права на лево по диагонали.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void moveAttackPawnLeftPoint() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Pawn(new Point(6, 5), true, "Pawn");
        chessGame.getBoard().addFigure(figure);
        chessGame.getBoard().addFigure(new Pawn(new Point(5, 4), false, "Pawn"));
        chessGame.moveFigure(new Point(6, 5), new Point(5, 4));
        assertThat(chessGame.getBoard().getFigure(new Point(5, 4)), is(figure));
    }

    /**
     * Атака белой пешки с лева на право по диагонали.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void moveAttackPawnRightPoint() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Pawn(new Point(6, 5), true, "Pawn");
        chessGame.getBoard().addFigure(figure);
        chessGame.getBoard().addFigure(new Pawn(new Point(5, 6), false, "Pawn"));
        chessGame.moveFigure(new Point(6, 5), new Point(5, 6));
        assertThat(chessGame.getBoard().getFigure(new Point(5, 6)), is(figure));
    }

    /**
     * Не валидный ход белой пешки.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void whenMoveWhitePawnInvalidThenDropException() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.getBoard().addFigure(new Pawn(new Point(6, 5), true, "Pawn"));
        chessGame.moveFigure(new Point(6, 5), new Point(4, 3));
    }

    /**
     * Тест хода по диагонали.
     */
    @Test
    public void whenMoveDiagonalThenValidMove() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.getBoard().addFigure(new Pawn(new Point(3, 4), false, "Pawn"));
        chessGame.getBoard().addFigure(new Pawn(new Point(4, 3), true, "Pawn"));
        chessGame.moveFigure(new Point(3, 4), new Point(4, 3));
        assertFalse(chessGame.getBoard().getFigure(new Point(4, 3)).isWhite());
    }

    /**
     * Тест метода toString у Figure.
     */
    @Test
    public void figureToStringTest() {
        final String expected = "Figure{point = 5.2, isWhite = false, type = 'Knight'}";
        final Figure figure = new Knight(new Point(5, 2), false, "Knight");
        assertThat(figure.toString(), is(expected));
    }

    /**
     * Тест броска исключения если передается не валидный Point.
     * @throws IllegalMoveException Бросает если Point не валидный.
     */
    @Test(expected = IllegalMoveException.class)
    public void getFigureTest() throws IllegalMoveException {
        final Board board = new Board(boardSize);
        board.getFigure(new Point(-1, 10));
    }

    /**
     * Тест метода toString у Point.
     */
    @Test
    public void pointToStringTest() {
        final Point point = new Point(3, 3);
        final String expected = "Point{x = 3, y = 3}";
        assertThat(point.toString(), is(expected));
    }

    /**
     * Валидный ход короля по диагонали вверх.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void validMoveKingFromDiagonalLeft() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new King(new Point(3, 3), false, "King");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 3), new Point(2, 3));
        assertThat(chessGame.getBoard().getFigure(new Point(2, 3)), is(figure));
    }

    /**
     * Валидный ход короля по диагоняли в низ.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void validMoveKingFromDiagonalDown() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new King(new Point(3, 3), false, "King");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 3), new Point(4, 2));
        assertThat(chessGame.getBoard().getFigure(new Point(4, 2)), is(figure));
    }

    /**
     * Не валидных ход короля по диагонали.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void moveKingInvalidDiagonalPoint() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.getBoard().addFigure(new King(new Point(3, 3), false, "King"));
        chessGame.moveFigure(new Point(3, 3), new Point(5, 5));
    }

    /**
     * Не валидных ход короля по линии.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void moveKingInvalidLinePoint() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.getBoard().addFigure(new King(new Point(3, 3), false, "King"));
        chessGame.moveFigure(new Point(3, 3), new Point(5, 3));
    }

    /**
     * Валидный ход короля по линии.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void validMoveKingFromLine() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new King(new Point(3, 3), false, "King");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 3), new Point(3, 2));
        assertThat(chessGame.getBoard().getFigure(new Point(3, 2)), is(figure));
    }

    /**
     * Ход королевы по линии в низ.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void moveQueenFromLineDown() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Queen(new Point(3, 3), false, "Queen");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 3), new Point(3, 7));
        assertThat(chessGame.getBoard().getFigure(new Point(3, 7)), is(figure));
    }

    /**
     * Ход королевы по диагонали в низ.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void moveQueenFromDiagonalDown() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Queen(new Point(3, 3), false, "Queen");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 3), new Point(6, 0));
        assertThat(chessGame.getBoard().getFigure(new Point(6, 0)), is(figure));
    }

    /**
     * Ход королевы по линии в лево.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void moveQueenLineFromLeft() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Queen(new Point(3, 3), false, "Queen");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 3), new Point(3, 0));
        assertThat(chessGame.getBoard().getFigure(new Point(3, 0)), is(figure));
    }

    /**
     * Ход королевы по линии вверх.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test
    public void moveQueenFromLineTop() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        final Figure figure = new Queen(new Point(3, 3), false, "Queen");
        chessGame.getBoard().addFigure(figure);
        chessGame.moveFigure(new Point(3, 3), new Point(0, 3));
        assertThat(chessGame.getBoard().getFigure(new Point(0, 3)), is(figure));
    }

    /**
     * Не валидный ход ладьи.
     * @throws IllegalMoveException Если ход не валидный.
     * @throws NoFigureException Если не выбрана фигура для хода.
     */
    @Test(expected = IllegalMoveException.class)
    public void invalidMoveROck() throws IllegalMoveException, NoFigureException {
        final ChessGame chessGame = new ChessGame(new Board(boardSize));
        chessGame.getBoard().addFigure(new Rock(new Point(0, 0), false, "Rock"));
        chessGame.moveFigure(new Point(0, 0), new Point(7, 7));
    }

    /**
     * Тест метода getType у фигуры.
     */
    @Test
    public void getTypeFigureTest() {
        final Figure figure = new Pawn(new Point(1, 1), false, "Pawn");
        final String expected = "Pawn";
        assertThat(figure.getType(), is(expected));
    }

    /**
     * Тест метода equals возвращает true если фигуры равны.
     */
    @Test
    public void whenTwoFigureEqualsThenReturnTrue() {
        final Figure figure = new Pawn(new Point(1, 1), false, "Pawn");
        final Figure figure2 = new Pawn(new Point(1, 1), false, "Pawn");
        final boolean expected = true;
        assertThat(figure.equals(figure2), is(expected));
    }

    /**
     * Тест метода equals возвращает false если фигуры не равны.
     */
    @Test
    public void whenTwoFigureNotEqualsThenReturnFalse() {
        final Figure figure = new Pawn(new Point(1, 1), false, "Pawn");
        final Figure figure2 = new Pawn(new Point(1, 1), true, "Pawn");
        final boolean expected = false;
        assertThat(figure.equals(figure2), is(expected));
    }

    /**
     * Тест метода клон.
     */
    @Test
    public void cloneFigureTest() {
        final Figure figure = new Rock(new Point(1, 1), false, "Rock");
        assertThat(figure.clone(new Point(1, 2)), is(figure));
    }
}