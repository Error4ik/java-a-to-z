package ru.job4j.dataBase;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.PoolDataSource;
import ru.job4j.model.Vacancy;
import ru.job4j.settings.Settings;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Vacancy to database test.
 *
 * @author Alexey Voronin.
 * @since 03.07.2017.
 */
public class VacancyToDataBaseTest {

    /**
     * Settings.
     */
    private final Settings settings = new Settings();

    /**
     * Url to connect menu_tracker.
     */
    private final String vacancyUrl = settings.getValue("url.vacancy");

    /**
     * User name to connect.
     */
    private final String userName = settings.getValue("user.name");

    /**
     * Password to connect.
     */
    private final String password = settings.getValue("password");

    /**
     * Url create DB.
     */
    private final String url = settings.getValue("url");

    /**
     * DataSource.
     */
    private final DataSource dataSource = PoolDataSource.setupDataSource(vacancyUrl, userName, password);

    /**
     * Create adn drop database.
     */
    private final CreateDB createDB = new CreateDB(url, userName, password);

    /**
     * Crete and drop table class.
     */
    private final CreateTable createTable = new CreateTable(dataSource);

    /**
     * Init menu_tracker, create DB and tables.
     */
    @Before
    public void init() {
        createTable.createTable();
    }

    /**
     * Method addVacancy.
     */
    @Test
    public void whenAddVacancyShouldVacancyAddsToDataBase() {
        final VacancyToDataBase toDataBase = new VacancyToDataBase(dataSource);
        final Vacancy vacancy = new Vacancy("test", "test", new Date(117, 0, 0, 0, 0));
        final int expectedValue = 1;

        toDataBase.addVacancy(vacancy);
        final int actualValue = toDataBase.getNumberOfRows();

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Method getVacancyById.
     */
    @Test
    public void whenGetVacancyByIdShouldReturnValidVacancy() {
        final VacancyToDataBase toDataBase = new VacancyToDataBase(dataSource);
        final Vacancy firstVacancy = new Vacancy("test", "test", new Date(117, 0, 0, 0, 0));
        final Vacancy secondVacancy = new Vacancy("test1", "test1", new Date(117, 0, 0, 0, 0));

        toDataBase.addVacancy(firstVacancy);
        toDataBase.addVacancy(secondVacancy);

        final Vacancy actualValue = toDataBase.getVacancyByID(2);

        assertThat(actualValue.getTitle(), is(secondVacancy.getTitle()));
    }

    /**
     * Method getTheLatestVacancyDate.
     */
    @Test
    public void whenGetTheLatestVacancyDateShouldReturnAFreshDate() {
        final VacancyToDataBase toDataBase = new VacancyToDataBase(dataSource);
        final Vacancy firstVacancy = new Vacancy("test", "test", new Date(117, 0, 0, 0, 0));
        final Vacancy secondVacancy = new Vacancy("Test2", "Test2", new Date(117, 11, 5, 0, 0));
        final long expectedValue = secondVacancy.getCreateDate().getTime();

        toDataBase.addVacancy(firstVacancy);
        toDataBase.addVacancy(secondVacancy);

        final long actualValue = toDataBase.getTheLatestVacancyDate();

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Method removeVacancyById.
     */
    @Test
    public void whenRemoveVacancyThenReturnsTheNumberOfRowsDeleted() {
        final VacancyToDataBase toDataBase = new VacancyToDataBase(dataSource);
        final Vacancy firstVacancy = new Vacancy("test", "test", new Date(117, 0, 0, 0, 0));
        final Vacancy secondVacancy = new Vacancy("Test2", "Test2", new Date(117, 11, 5, 0, 0));
        final Vacancy thirdVacancy = new Vacancy("Test3", "Test3", new Date(117, 3, 25, 12, 25));
        final int expectedValue = 1;

        toDataBase.addVacancy(firstVacancy);
        toDataBase.addVacancy(secondVacancy);
        toDataBase.addVacancy(thirdVacancy);

        final int actualValue = toDataBase.removeVacancyByID(3);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Method getAllVacancy.
     */
    @Test
    public void whenGetAllVacancyShouldReturnAllVacancyFromDataBase() {
        final VacancyToDataBase toDataBase = new VacancyToDataBase(dataSource);
        final Vacancy firstVacancy = new Vacancy("test", "test", new Date(117, 0, 0, 0, 0));
        final Vacancy secondVacancy = new Vacancy("Test2", "Test2", new Date(117, 11, 5, 0, 0));
        final Vacancy thirdVacancy = new Vacancy("Test3", "Test3", new Date(117, 3, 25, 12, 25));
        final int expectedListSize = 3;

        toDataBase.addVacancy(firstVacancy);
        toDataBase.addVacancy(secondVacancy);
        toDataBase.addVacancy(thirdVacancy);

        final List<Vacancy> actualList = toDataBase.getAllVacancy();

        assertThat(actualList.size(), is(expectedListSize));
    }
}