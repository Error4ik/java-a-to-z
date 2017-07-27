package ru.job4j.dao;

import ru.job4j.model.Country;

import java.util.List;

/**
 * Country dao.
 *
 * @author Alexey Voronin.
 * @since 23.07.2017.
 */
public interface CountryDao {

    /**
     * Add country.
     *
     * @param country country.
     * @return id of the added entry.
     */
    int addCountry(final Country country);

    /**
     * Return country by id.
     *
     * @param id id.
     * @return country.
     */
    Country getCountryById(final int id);

    /**
     * Remove Country.
     *
     * @param id country id for the delete.
     */
    void removeCountryById(final int id);

    /**
     * Get all country.
     *
     * @return country list.
     */
    List<Country> getAllCountry();

    /**
     * Get country by name.
     *
     * @param name name.
     * @return country.
     */
    Country getCountryByName(final String name);
}
