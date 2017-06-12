package ru.job4j.startCLI;

import org.apache.log4j.Logger;
import ru.job4j.CreateDB;
import ru.job4j.CreateTable;
import ru.job4j.PoolDataSource;
import ru.job4j.action.Action;
import ru.job4j.dao.Tracker;
import ru.job4j.exception.MenuOutException;
import ru.job4j.input.ConsoleInput;
import ru.job4j.input.Input;
import ru.job4j.menu_tracker.MenuTracker;
import ru.job4j.settings.Settings;

import javax.sql.DataSource;

/**
 * Start program.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class StartUI {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(StartUI.class);

    /**
     * Input.
     */
    private final Input input;

    /**
     * Menu menu_tracker.
     */
    private final MenuTracker menuTracker;

    /**
     * CreateDB.
     */
    private final CreateDB createDB;

    /**
     * Create and drop table.
     */
    private final CreateTable createTable;

    /**
     * Constructor.
     *
     * @param input       user input.
     * @param menuTracker menu tracker.
     * @param createDB    createDB.
     * @param createTable createTable.
     */
    public StartUI(final Input input, final MenuTracker menuTracker,
                   final CreateDB createDB, final CreateTable createTable) {
        this.input = input;
        this.menuTracker = menuTracker;
        this.createDB = createDB;
        this.createTable = createTable;
    }

    /**
     * Starts the menu_tracker, displays the menu_tracker menu and prompts the user for input.
     */
    public void start() {
        int value = -1;
        final int exitNumber = 0;
        String line = input.getInput("If this is the first run of the program, "
                + "then you need to create a database and tables, create one? y/n: ").trim();
        if (line.equals("y")) {
            createDB.createDB();
            createTable.createTable();
        }
        do {
            menuTracker.showMenu();
            try {
                value = Integer.parseInt(input.getInput("Choice Action:\n").trim());
                validEnter(value);
            } catch (NumberFormatException e) {
                LOGGER.error("This is not a number, try again! ", e);
            } catch (MenuOutException e) {
                LOGGER.error("Invalid menu number, try again! ", e);
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

        DataSource dataSource = PoolDataSource.setupDataSource(trackerUrl, userName, password);

        Tracker tracker = new Tracker(dataSource);
        CreateDB createDB = new CreateDB(url, userName, password);
        CreateTable createTable = new CreateTable(dataSource);
        MenuTracker menuTracker = new MenuTracker(tracker, input);
        menuTracker.fillAction();

        new StartUI(input, menuTracker, createDB, createTable).start();
    }
}
