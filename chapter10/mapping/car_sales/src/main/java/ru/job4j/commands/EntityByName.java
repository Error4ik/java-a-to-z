package ru.job4j.commands;

import org.hibernate.Session;

/**
 * Interface return entity by name.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public interface EntityByName<T> {

    /**
     * Get entity by name.
     * @param session hibernate session.
     * @return entity by name.
     */
    T getEntityByName(final Session session);
}
