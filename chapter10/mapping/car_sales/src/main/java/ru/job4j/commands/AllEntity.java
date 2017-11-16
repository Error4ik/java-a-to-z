package ru.job4j.commands;

import org.hibernate.Session;

import java.util.List;

/**
 * Interface return all entities the any object.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public interface AllEntity<T> {

    /**
     * Get all entities.
     *
     * @param session hibernate session.
     * @return list of entities.
     */
    List<T> getAll(Session session);
}
