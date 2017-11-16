package ru.job4j.commands;

import org.hibernate.Session;

/**
 * Interface return entity by id.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public interface EntityById<T> {

    /**
     * Get entity by id.
     *
     * @param session hibernate session.
     * @return entity by id.
     */
    T getEntityById(final Session session);
}
