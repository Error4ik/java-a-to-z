package ru.job4j.comand;

import org.hibernate.Session;

/**
 * Interface get entity by id.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 15.11.2017.
 */
public interface EntityById<T> {

    /**
     * Get entity by id.
     *
     * @param session hibernate session.
     * @return entity by id.
     */
    T getById(Session session);
}
