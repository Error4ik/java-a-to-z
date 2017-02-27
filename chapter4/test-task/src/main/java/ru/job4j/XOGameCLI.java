package ru.job4j;

import ru.job4j.model.Field;
import ru.job4j.view.ConsoleView;
import ru.job4j.view.IView;

import java.util.Scanner;

/**
 * Run XO game.
 *
 * @author Alexey Voronin.
 * @since 26.02.2017.
 */
public class XOGameCLI {

    /**
     * Main method contains main game loop.
     * @param args args.
     */
    public static void main(String[] args) {
        final XOGameCLI gameCLI = new XOGameCLI();
        final IView view = new ConsoleView();

        view.showMessage("Enter field size: Eg 3 or 5 or 10");
        final int size = gameCLI.getTheSelectedFieldSize();

        final Field field = new Field(size);
        StartGame startGame = new StartGame("XOGame", field);

        view.showMessage("Select figure to game: Eg X or O if enter that the other computer vs computer");
        String start = gameCLI.getTheSelectedFigurePlayer();

        view.showField(field);
        while (startGame.move(start)) {
            view.showField(field);
        }
    }

    /**
     * Get the selected field size.
     * @return input size or default value.
     */
    private int getTheSelectedFieldSize() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        return 3;
    }

    /**
     * Get the selected figure player.
     * @return user input.
     */
    private String getTheSelectedFigurePlayer() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
