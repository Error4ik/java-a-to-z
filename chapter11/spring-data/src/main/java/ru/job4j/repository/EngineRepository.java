package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.Engine;

/**
 * Engine repository.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Repository
public interface EngineRepository extends CrudRepository<Engine, Integer> {

    /**
     * Return engine by name.
     *
     * @param name name.
     * @return engine.
     */
    Engine findByName(final String name);
}
