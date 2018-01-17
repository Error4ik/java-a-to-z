package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.CarModel;

import java.util.List;

/**
 * Car model repository.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Repository
public interface CarModelsRepository extends CrudRepository<CarModel, Integer> {
    /**
     * Return car model by name.
     *
     * @param name name.
     * @return car model.
     */
    CarModel findByName(final String name);

    /**
     * Return list car model by brand id.
     *
     * @param id brand id.
     * @return list models.
     */
    List<CarModel> findByCarBrandId(final int id);
}
