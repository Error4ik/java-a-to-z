package ru.job4j.storage;

import java.util.List;

/**
 * Storage interface.
 *
 * @author Alexey Voronin.
 * @since 04.12.2017.
 */
public interface Storage<T> {

    /**
     * Save entity.
     *
     * @param entity object to save.
     * @return saved object.
     */
    T save(final T entity);

    /**
     * Get object by id.
     *
     * @param id id.
     * @return object.
     */
    T getById(final int id);

    /**
     * Get all object.
     *
     * @return list objects.
     */
    List<T> getAllEntity();

    /**
     * Update entity.
     *
     * @param entity entity for update.
     * @return updated object.
     */
    int updateEntity(final T entity);

    /**
     * Delete entity.
     *
     * @param entity entity for deleted.
     * @return deleted entity.
     */
    int deleteEntity(final T entity);
}
