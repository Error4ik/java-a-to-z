package ru.job4j.dao;

import ru.job4j.model.City;

import java.util.List;

/**
 * City dao.
 *
 * @author Alexey Voronin.
 * @since 23.07.2017.
 */
public interface CityDao {

    /**
     * Add city.
     *
     * @param city city.
     * @return id of the added entry.
     */
    int addCity(final City city);

    /**
     * Return city by id.
     *
     * @param id id.
     * @return city.
     */
    City getCityById(final int id);

    /**
     * Remove city.
     *
     * @param id city id for the delete.
     * @return number of deleted records.
     */
    int removeCityById(final int id);

    /**
     * Get all city.
     *
     * @return city list.
     */
    List<City> getAllCity();

    /**
     * Get all city by country id.
     *
     * @param id country id.
     * @return list cities.
     */
    List<City> getAllCityByCountry(final int id);

    /**
     * Get city by name.
     *
     * @param name name.
     * @return city.
     */
    City getCityByName(final String name);
}
