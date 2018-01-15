package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.City;

/**
 * City repository.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    /**
     * Return city by name.
     *
     * @param name name.
     * @return city.
     */
    City findByName(final String name);
}
