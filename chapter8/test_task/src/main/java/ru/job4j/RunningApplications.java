package ru.job4j;

import ru.job4j.dataBase.CreateDB;
import ru.job4j.dataBase.CreateTable;
import ru.job4j.dataBase.VacancyToDataBase;
import ru.job4j.model.Vacancy;
import ru.job4j.parser.Parser;
import ru.job4j.settings.Settings;

import javax.sql.DataSource;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Run app.
 *
 * @author Alexey Voronin.
 * @since 27.06.2017.
 */
public class RunningApplications extends TimerTask {

    /**
     * Settings from file.
     */
    private final Settings settings = new Settings();

    /**
     * DataSource.
     */
    private final DataSource dataSource;

    /**
     * Class for working with the database.
     */
    private final VacancyToDataBase vacancyToDataBase;

    /**
     * Url.
     */
    private final String url;

    /**
     * Vacancy database url.
     */
    private final String vacancyUrl;

    /**
     * User name.
     */
    private final String userName;

    /**
     * Password.
     */
    private final String password;

    /**
     * Constructor.
     */
    public RunningApplications() {
        this.url = settings.getValue("url");
        this.vacancyUrl = settings.getValue("url.vacancy");
        this.userName = settings.getValue("user.name");
        this.password = settings.getValue("password");
        this.dataSource = PoolDataSource.setupDataSource(vacancyUrl, userName, password);
        this.vacancyToDataBase = new VacancyToDataBase(dataSource);
        this.createDbAndTable();
    }

    @Override
    public void run() {
        boolean firstStart = vacancyToDataBase.getNumberOfRows() == 0;
        Parser parser = new Parser();
        long time = 0;
        if (firstStart) {
            parser.parseToSite(firstStart, time);
        } else {
            time = vacancyToDataBase.getTheLatestVacancyDate();
            parser.parseToSite(firstStart, time);
        }
        for (Vacancy vacancy : parser.getVacancies()) {
            vacancyToDataBase.addVacancy(vacancy);
        }
    }

    /**
     * Create database and table.
     */
    private void createDbAndTable() {
        CreateDB createDB = new CreateDB(url, userName, password);
        CreateTable createTable = new CreateTable(dataSource);
        createDB.createDB();
        createTable.createTable();
    }

    /**
     * Main method run program.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        Timer time = new Timer();
        RunningApplications running = new RunningApplications();
        final Settings settings = new Settings();
        long period = Long.parseLong(settings.getValue("repeat.period"));
        time.schedule(running, 0, period);
    }
}
