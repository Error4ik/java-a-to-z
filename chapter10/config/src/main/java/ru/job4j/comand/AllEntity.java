package ru.job4j.comand;

import org.hibernate.Session;

import java.util.List;

/**
 * Interface for all entity.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 15.11.2017.
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
