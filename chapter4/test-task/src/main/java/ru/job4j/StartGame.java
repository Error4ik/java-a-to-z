package ru.job4j;

import ru.job4j.controller.CurrentMoveController;
import ru.job4j.controller.MoveController;
import ru.job4j.controller.WinnerController;
import ru.job4j.exception.AlreadyOccupiedException;
import ru.job4j.exception.InvalidPointException;
import ru.job4j.model.Field;
import ru.job4j.model.Figure;
import ru.job4j.model.Point;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Class starts the game Tic Tac Toe.
 *
 * @author Alexey Voronin.
 * @since 26.02.2017.
 */
public class StartGame {

    /**
     * Separator.
     */
    private String separator = System.getProperty("line.separator");

    /**
     * Move controller.
     * It checks whether it is possible to put a figure in a cage, and if you can put it.
     */
    private final MoveController moveController = new MoveController();

    /**
     * Current move controller.
     * Determines who goes at the moment.
     */
    private final CurrentMoveController currentMoveController = new CurrentMoveController();

    /**
     * Winner controller.
     * Determines the winner.
     */
    private final WinnerController winnerController = new WinnerController();

    /**
     * Game name.
     */
    private final String name;

    /**
     * Game field.
     */
    private final Field field;

    /**
     * Constructor.
     *
     * @param name  game name.
     * @param field game field.
     */
    public StartGame(final String name, final Field field) {
        this.name = name;
        this.field = field;
    }

    /**
     * Main game cycle.
     *
     * @return true If the game continues.
     * @param start type of user-selected figure.
     */
    public boolean move(final String start) {
        try {
            Figure winner = winnerController.getWinner(this.getField());
            if (winner != null) {
                System.out.println();
                System.out.printf("Congratulation winner: %s", winner);
                return false;
            }
            Figure currentFigure = currentMoveController.currentMove(this.getField());
            if (currentFigure == null) {
                System.out.println("Draw!!!");
                return false;
            }

            if (currentFigure.toString().equalsIgnoreCase(start)) {
                System.out.printf("Please enter move point for: %s%s", currentFigure, separator);
                moveController.setFigure(this.getField(), this.getUserPoint(), currentFigure);
            } else {
                getPointGenerate(currentFigure);
            }

        } catch (InvalidPointException | AlreadyOccupiedException e) {
            //e.printStackTrace();
            System.out.printf("Error: %s, try again.%s", e.getMessage(), separator);
        }
        return true;
    }

    /**
     * Get.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get.
     *
     * @return field.
     */
    public Field getField() {
        return field;
    }

    /**
     * Generate random point for move computer, and set figure to the field.
     * If an exception is thrown, the method calls itself until the will not be able to set the figure.
     * @param currentFigure a figure which now goes.
     */
    private void getPointGenerate(final Figure currentFigure) {
        int fieldSize = this.getField().getFieldSize();
        Random random = new Random();
        Point point = new Point(random.nextInt(fieldSize), random.nextInt(fieldSize));
        try {
            moveController.setFigure(this.getField(), point, currentFigure);
            System.out.printf("Computer move %s %s%s", point.getX(), point.getY(), separator);
        } catch (AlreadyOccupiedException | InvalidPointException e) {
            this.getPointGenerate(currentFigure);
        }


    }

    /**
     * Get user point.
     *
     * @return point.
     */
    private Point getUserPoint() {
        Scanner scanner = new Scanner(System.in);
        try {
            return new Point(scanner.nextInt(), scanner.nextInt());
        } catch (final InputMismatchException e) {
            System.out.println("You entered is not correct point, try again.");
            //e.printStackTrace();
            return getUserPoint();
        }
    }
}
