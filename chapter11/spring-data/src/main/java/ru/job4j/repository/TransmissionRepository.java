package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Transmission;

/**
 * Transmission repository.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Repository
public interface TransmissionRepository extends CrudRepository<Transmission, Integer> {

    /**
     * Return transmission by name.
     *
     * @param name name.
     * @return transmission.
     */
    Transmission findByName(final String name);
}
