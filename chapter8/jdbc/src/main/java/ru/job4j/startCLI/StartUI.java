package ru.job4j.startCLI;

import ru.job4j.CreateDB;
import ru.job4j.action.Action;
import ru.job4j.exception.MenuOutException;
import ru.job4j.input.ConsoleInput;
import ru.job4j.input.Input;
import ru.job4j.settings.Settings;
import ru.job4j.tracker.MenuTracker;
import ru.job4j.tracker.Tracker;

/**
 * Start program.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class StartUI {

    /**
     * Input.
     */
    private Input input;

    /**
     * Menu tracker.
     */
    private MenuTracker menuTracker;

    /**
     * Constructor.
     *
     * @param input       user input.
     * @param menuTracker menu tracker.
     */
    public StartUI(final Input input, final MenuTracker menuTracker) {
        this.input = input;
        this.menuTracker = menuTracker;
    }

    /**
     * Starts the tracker, displays the tracker menu and prompts the user for input.
     */
    public void start() {
        int value = -1;
        final int exitNumber = 0;
        String createBDAndTableAnew = input.getInput("If this is the first run of the program, "
                + "then you need to create a database and tables, create one? y/n: ").trim();
        if (createBDAndTableAnew.equals("y")) {
            menuTracker.getCreateDB().createDB();
            menuTracker.getTracker().createTable();
        }
        do {
            menuTracker.showMenu();
            try {
                value = Integer.parseInt(input.getInput("Choice Action:\n").trim());
                validEnter(value);
            } catch (NumberFormatException e) {
                System.out.println("This is not a number, try again!");
            }
        } while (value != exitNumber);
    }

    /**
     * Checks user input. If the user has selected an unavailable menu item, an exception is thrown.
     *
     * @param number User input.
     * @throws MenuOutException unavailable menu item, an exception is thrown.
     */
    private void validEnter(final int number) throws MenuOutException {
        boolean valid = false;
        for (Action action : menuTracker.getActions()) {
            if (Integer.parseInt(action.getId()) == number) {
                valid = true;
                break;
            }
        }

        if (valid) {
            menuTracker.select(number);
        } else {
            throw new MenuOutException("MenuOutException");
        }
    }

    /**
     * Main method.
     *
     * @param args array.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Settings settings = new Settings();

        String trackerUrl = settings.getValue("trackerUrl");
        String userName = settings.getValue("userName");
        String password = settings.getValue("password");
        String url = settings.getValue("url");

        Tracker tracker = new Tracker(trackerUrl, userName, password);
        CreateDB createDB = new CreateDB(url, userName, password);
        MenuTracker menuTracker = new MenuTracker(tracker, input, createDB);
        menuTracker.fillAction();
        new StartUI(input, menuTracker).start();
    }
}
