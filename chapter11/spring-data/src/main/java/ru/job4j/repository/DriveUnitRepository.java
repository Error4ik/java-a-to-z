package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.DriveUnit;

/**
 * Drive unit repository.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Repository
public interface DriveUnitRepository extends CrudRepository<DriveUnit, Integer> {

    /**
     * Return drive unit by name.
     *
     * @param name name.
     * @return drive unit.
     */
    DriveUnit findByName(final String name);
}
