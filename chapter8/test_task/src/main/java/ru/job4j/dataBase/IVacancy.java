package ru.job4j.dataBase;

import ru.job4j.model.Vacancy;

import java.util.List;

/**
 * Add vacancy to data base.
 *
 * @author Alexey Voronin.
 * @since 28.06.2017.
 */
public interface IVacancy {

    /**
     * Add vacancy.
     *
     * @param vacancy vacancy.
     * @return id.
     */
    int addVacancy(final Vacancy vacancy);

    /**
     * Get vacancy by id.
     *
     * @param id id.
     * @return vacancy by id.
     */
    Vacancy getVacancyByID(final int id);

    /**
     * Remove vacancy by id.
     *
     * @param id id.
     * @return Number of deleted records.
     */
    int removeVacancyByID(final int id);

    /**
     * Ger all vacancy to database.
     *
     * @return all vacancy.
     */
    List<Vacancy> getAllVacancy();

    /**
     * Get the latest vacancy date.
     *
     * @return vacancy.
     */
    long getTheLatestVacancyDate();

    /**
     * Get number of rows.
     *
     * @return number of rows from table.
     */
    int getNumberOfRows();
}
