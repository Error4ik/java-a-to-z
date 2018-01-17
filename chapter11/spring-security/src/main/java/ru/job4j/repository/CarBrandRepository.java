package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.CarBrand;

/**
 * Car brand repository.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Repository
public interface CarBrandRepository extends CrudRepository<CarBrand, Integer> {
    /**
     * Return car brand by name.
     *
     * @param name name.
     * @return car brand.
     */
    CarBrand findByName(final String name);
}
