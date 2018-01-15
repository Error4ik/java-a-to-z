package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.CarBody;

/**
 * Car body repository.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Repository
public interface CarBodyRepository extends CrudRepository<CarBody, Integer> {
    /**
     * Return car body by name.
     *
     * @param name name.
     * @return car body.
     */
    CarBody findByName(final String name);
}
