package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Car;

/**
 * Car repository.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
}
